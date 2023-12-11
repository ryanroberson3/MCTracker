import McTrackerClient from '../api/mcTrackerClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';
import { formatDateToMMDDYYYY } from '../util/dateUtils';
import { parseDateFromMMDDYYYY } from '../util/dateUtils';



class UpdateGameLog extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'clientLoaded', 'populateDropdown', 'submit', 'redirectToViewGameLog', 'viewGameLog', 'addHero', 'removeHero'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.redirectToViewGameLog);
        this.dataStore.addChangeListener(this.viewGameLog);
        this.header = new Header(this.dataStore);
    }

    async clientLoaded() {
        const urlParams = new URLSearchParams(window.location.search);
        const gameId = urlParams.get('id');

        const originalGameLog = await this.client.viewGameLog(gameId);
        this.dataStore.set('originalGameLog', originalGameLog);

        const villains = await this.client.getCharactersByRole("Villain");
        this.dataStore.set('villains', villains);

        const heroes = await this.client.getCharactersByRole("Hero");
        this.dataStore.set('heroes', heroes);

        this.populateDropdown('villainDropdown', villains);
        this.populateDropdown('heroDropdown', heroes);

        date.value = formatDateToMMDDYYYY(originalGameLog.date);
        outcomeWL.value = originalGameLog.outcomeWL;
        villainDropdown.value = originalGameLog.villain;
        
        // originalGameLog.heroes.forEach(hero => {
        //     const heroItem = document.createElement('div');
        //     heroItem.textContent = hero;
        //     selectedHeroesBox.appendChild(heroItem);
        // });

        // originalGameLog.heroes.forEach(hero => {
        //     const option = document.createElement('option');
        //     option.value = hero;
        //     option.text = hero;
        //     heroDropdown.appendChild(option);
        // });
        originalGameLog.heroes.forEach(hero => {
            const trimmedHero = hero.trim(); // Trim leading and trailing spaces
            const heroItem = document.createElement('div');
        
            // Create a remove button with a Font Awesome circle x-mark icon
            const removeButton = document.createElement('button');
            removeButton.innerHTML = '<i class="fas fa-times-circle"></i>'; // Use the times-circle icon
        
            // Add a click event to remove the hero
            removeButton.addEventListener('click', () => this.removeHero(trimmedHero, heroItem));
        
            // Create a span for the hero name
            const heroNameSpan = document.createElement('span');
            heroNameSpan.textContent = trimmedHero; // Use the trimmed hero name
        
            // Append the hero name span and the remove button to the hero item
            heroItem.appendChild(heroNameSpan);
            heroItem.appendChild(removeButton);
        
            // Append the hero item to the selectedHeroesBox
            selectedHeroesBox.appendChild(heroItem);
        });
        
        originalGameLog.heroes.forEach(hero => {
            const option = document.createElement('option');
            option.value = hero.trim(); // Trim leading and trailing spaces
            option.text = hero.trim(); // Trim leading and trailing spaces
            heroDropdown.appendChild(option);
        });
        
        
        
        
        
        

        const aspectCheckboxes = document.querySelectorAll('input[name="aspect"]');
        originalGameLog.aspect.forEach(originalAspect => {
        aspectCheckboxes.forEach(checkbox => {
            if (checkbox.value === originalAspect) {
                checkbox.checked = true;
            }
        });
    });

    }


    mount() {
        this.header.addHeaderToPage();
        this.client = new McTrackerClient();
        this.clientLoaded();

        document.getElementById('submit-btn').addEventListener('click', this.submit);
        document.getElementById('addHero').addEventListener('click', this.addHero.bind(this));
    }

    addHero() {
        const heroDropdown = document.getElementById('heroDropdown');
        const selectedHeroesBox = document.getElementById('selectedHeroesBox');
    
        const selectedOptions = heroDropdown.selectedOptions;
        const selectedHeroes = Array.from(selectedOptions).map(option => option.value);
    
        heroDropdown.selectedIndex = -1;
    
        selectedHeroes.forEach(hero => {
            const heroItem = document.createElement('div');
    
            // Create a remove button with a Font Awesome circle x-mark icon
            const removeButton = document.createElement('button');
            removeButton.innerHTML = '<i class="fas fa-times-circle"></i>'; // Use the times-circle icon
    
            // Add a click event to remove the hero
            removeButton.addEventListener('click', () => this.removeHero(hero, heroItem));
    
            // Add the hero name without any additional characters
            heroItem.textContent = hero;
    
            // Append the remove button and the hero name
            heroItem.appendChild(removeButton);
            selectedHeroesBox.appendChild(heroItem);
        });
    }

    removeHero(hero, heroItem) {
        // Remove the hero from the selectedHeroesBox
        heroItem.remove();
    
        // Unselect the hero in the heroDropdown
        const heroDropdown = document.getElementById('heroDropdown');
        const optionToRemove = Array.from(heroDropdown.options)
            .find(option => option.value === hero);
        
        if (optionToRemove) {
            optionToRemove.selected = false;
        }
    }
    

    async populateDropdown(dropdownId, characters) {
        const dropdown = document.getElementById(dropdownId);
        dropdown.innerHTML = "";

        const emptyOption = document.createElement('option');
        emptyOption.value = '';
        emptyOption.text = ' '; // Use a space or any other character for the label
        dropdown.appendChild(emptyOption);

        characters.forEach(character => {
            const option = document.createElement('option');
            option.value = character;
            option.text = character;
            dropdown.appendChild(option);
        });
    }

    async submit(evt) {
        const origGameLog = this.dataStore.get('originalGameLog');
        const origDate = parseDateFromMMDDYYYY(origGameLog.date);
        const origOutcomeWL = origGameLog.outcomeWL;
        const origAspects = origGameLog.aspect;
        const origHeroes = origGameLog.heroes;
        const origVillain = origGameLog.villain;



        evt.preventDefault();
        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');

        const updateButton = document.getElementById('submit-btn');
        const originalButton = updateButton.innerText;
        updateButton.innerText = 'Updating Game Log...';

        const urlParams = new URLSearchParams(window.location.search);
        const gameId = urlParams.get('id');
        let date = document.getElementById('date').value;
        if (!date) {
            date = formatDateToMMDDYYYY(origDate);
        }
        let outcomeWL = document.getElementById('outcomeWL').value;
        if (!outcomeWL) {
            outcomeWL = origOutcomeWL;
        }
        let aspectCheckboxes = document.querySelectorAll('input[name="aspect"]:checked');
        let aspect = Array.from(aspectCheckboxes).map(checkbox => checkbox.value);
        if (aspectCheckboxes.length === 0) {
            aspectCheckboxes = origAspects;
            aspect = aspectCheckboxes;
        }

        const villainDropdown = document.getElementById('villainDropdown');
        let villain = villainDropdown.value;
        if (!villain) {
            villain = origVillain;
        }
    
        const selectedHeroesBox = document.getElementById('selectedHeroesBox');
        let selectedHeroes = Array.from(selectedHeroesBox.children).map(heroItem => heroItem.textContent);
        if (selectedHeroes.length === 0) {
            selectedHeroes = origHeroes;
        }

        if (selectedHeroes.length > 4) {
            updateButton.innerText = origButtonText;
            errorMessageDisplay.innerText = 'Error: You can only select up to 4 heroes.';
            errorMessageDisplay.classList.remove('hidden');
            return;
        }

        const gameLog = await this.client.updateGameLog(
            gameId,
            date,
            outcomeWL,
            aspect,
            selectedHeroes,
            villain,
            (error) => {
                updateButton.innerText = originalButton;
                errorMessageDisplay.innerText = `Error: ${error.message}`;
                errorMessageDisplay.classList.remove('hidden');
        });
        this.dataStore.set('gameLog', gameLog);
    }


    viewGameLog() {
        const originalGameLog = this.dataStore.get('originalGameLog');
        if (originalGameLog == null) {
            return;
        }
    }


    redirectToViewGameLog() {
        const gameLog = this.dataStore.get('gameLog');
        if (gameLog != null) {
            setTimeout(() => {
                window.location.href = `/viewGameLog.html?id=${gameLog.gameId}`;
            }, 3000);
        }
    }
}

const main = async () => {
    const updateGameLog = new UpdateGameLog();
    updateGameLog.mount();
};

window.addEventListener('DOMContentLoaded', main);