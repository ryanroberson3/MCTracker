import McTrackerClient from '../api/mcTrackerClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';
import { formatDateToMMDDYYYY } from '../util/dateUtils';
import { parseDateFromMMDDYYYY } from '../util/dateUtils';



class UpdateGameLog extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'clientLoaded', 'populateDropdown', 'submit', 'redirectToViewGameLog', 'viewGameLog'], this);
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

        // Get selected heroes from the dropdown
        const selectedOptions = heroDropdown.selectedOptions;
        const selectedHeroes = Array.from(selectedOptions).map(option => option.value);

        // Clear the dropdown selection
        heroDropdown.selectedIndex = -1;

        // Display selected heroes in the box
        selectedHeroes.forEach(hero => {
            const heroItem = document.createElement('div');
            heroItem.textContent = hero;
            selectedHeroesBox.appendChild(heroItem);
        });
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
            createButton.innerText = origButtonText;
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
        document.getElementById('current-date').innerText = formatDateToMMDDYYYY(originalGameLog.date);
        document.getElementById('current-outcomeWL').innerText = originalGameLog.outcomeWL;
        document.getElementById('current-aspect').innerText = originalGameLog.aspect;
        document.getElementById('current-heroes').innerText = originalGameLog.heroes;
        document.getElementById('current-villain').innerText = originalGameLog.villain;
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