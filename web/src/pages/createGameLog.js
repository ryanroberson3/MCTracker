import McTrackerClient from '../api/mcTrackerClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';


class CreateGameLog extends BindingClass {

    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'populateDropdown', 'mount', 'submit', 'redirectToViewGameLog', 'addHero'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.redirectToViewGameLog);
        this.header = new Header(this.dataStore);
    }

    async clientLoaded() {
        const villains = await this.client.getCharactersByRole("Villain");
        this.dataStore.set('villains', villains);

        const heroes = await this.client.getCharactersByRole("Hero");
        this.dataStore.set('heroes', heroes);

        this.populateDropdown('villainDropdown', villains);
        this.populateDropdown('heroDropdown', heroes);
    }

    mount() {
        document.getElementById('create').addEventListener('click', this.submit.bind(this));
        this.header.addHeaderToPage();
        this.client = new McTrackerClient();
        this.clientLoaded();

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
        evt.preventDefault();
    
        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = '';
        errorMessageDisplay.classList.add('hidden');
    
        const createButton = document.getElementById('create');
        const origButtonText = createButton.innerText;
        createButton.innerText = 'Creating...';
    
        const date = document.getElementById('date').value;
        if(!date) {
            errorMessageDisplay.innerText = `You must select a date`;
            errorMessageDisplay.classList.remove('hidden');
            return;
        }
        const aspectCheckboxes = document.querySelectorAll('input[name="aspect"]:checked');
        const aspect = Array.from(aspectCheckboxes).map(checkbox => checkbox.value);
        if(aspectCheckboxes.length === 0) {
            errorMessageDisplay.innerText = `You must select at least 1 aspect`;
            errorMessageDisplay.classList.remove('hidden');
            return;
        }
    
        const outcomeWL = document.getElementById('outcomeWL').value;
        if(!outcomeWL) {
            errorMessageDisplay.innerText = `You must select an outcome`;
            errorMessageDisplay.classList.remove('hidden');
            return;
        }
    
        const villainDropdown = document.getElementById('villainDropdown');
        const villain = villainDropdown.value;
        if(!villain) {
            errorMessageDisplay.innerText = `You must select a villain`;
            errorMessageDisplay.classList.remove('hidden');
            return;
        }
    
        const selectedHeroesBox = document.getElementById('selectedHeroesBox');
        const selectedHeroes = Array.from(selectedHeroesBox.children).map(heroItem => heroItem.textContent);
        if(selectedHeroes.length === 0) {
            errorMessageDisplay.innerText = `You must select at least 1 hero`;
            errorMessageDisplay.classList.remove('hidden');
            return;
        }

        if (selectedHeroes.length > 4) {
            createButton.innerText = origButtonText;
            errorMessageDisplay.innerText = `You can only select up to 4 heroes.`;
            errorMessageDisplay.classList.remove('hidden');
            return;
        }

    
        const gameLog = await this.client.createGameLog(date, aspect, outcomeWL, villain, selectedHeroes, (error) => {
            createButton.innerText = origButtonText;
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });
    
        this.dataStore.set('gameLog', gameLog);
    }


    redirectToViewGameLog() {
        const gameLog = this.dataStore.get('gameLog');
        if (gameLog != null) {
            console.log("Redirecting to viewGameLog.html");
            window.location.href = `/viewGameLog.html?id=${gameLog.gameId}`;
        }
    }
}


const main = async () => {
    const createGameLog = new CreateGameLog();
    createGameLog.mount();
};

window.addEventListener('DOMContentLoaded', main);
