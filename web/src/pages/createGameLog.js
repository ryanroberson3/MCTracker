import McTrackerClient from '../api/mcTrackerClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';


class CreateGameLog extends BindingClass {

    constructor() {
        super();
        this.bindClassMethods(['mount', 'submit', 'redirectToViewGameLog', this]);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.redirectToViewGameLog);
        this.header = new Header(this.dataStore);
    }

    async clientLoaded() {
    }

    mount() {
        document.getElementById('create').addEventListener('click', this.submit.bind(this));
        this.header.addHeaderToPage();
        this.client = new McTrackerClient();
        this.clientLoaded();
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
        const aspect = document.getElementById('aspect').value;
        const outcome = document.getElementById('outcomeWL').value;

        const heroes = document.getElementById('heroes').value;
        const villain = document.getElementById('villain').value;

        const gameLog = await this.client.createGameLog(date, aspect, outcomeWL, villain, heroes, (error) => {
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
