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
        const aspectCheckboxes = document.querySelectorAll('input[name="aspect"]:checked');
        const aspect = Array.from(aspectCheckboxes).map(checkbox => checkbox.value);
    
        const outcomeWL = document.getElementById('outcomeWL').value;
    
        // Updated: Use the selected value directly from the dropdowns
        const villainDropdown = document.getElementById('villainDropdown');
        const villain = villainDropdown.value;
    
        const selectedHeroesBox = document.getElementById('selectedHeroesBox');
        const selectedHeroes = Array.from(selectedHeroesBox.children).map(heroItem => heroItem.textContent);

        if (selectedHeroes.length > 4) {
            // Handle the case where more than 4 heroes are selected (show an error, etc.)
            createButton.innerText = origButtonText;
            errorMessageDisplay.innerText = 'Error: You can only select up to 4 heroes.';
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
