import McTrackerClient from '../api/mcTrackerClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';
import { formatDateToMMDDYYYY } from '../util/dateUtils';


class UpdateGameLog extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'clientLoaded', 'submit', 'redirectToViewGameLog', 'viewGameLog'], this);
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
    }


    mount() {
        this.header.addHeaderToPage();
        this.client = new McTrackerClient();
        this.clientLoaded();
        document.getElementById('submit-btn').addEventListener('click', this.submit);
    }


    async submit(evt) {
        evt.preventDefault();
        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');

        const updateButton = document.getElementById('submit-btn');
        const originalButton = updateButton.innerText;
        updateButton.innerText = 'Updating Game Log...';

        const urlParams = new URLSearchParams(window.location.search);
        const gameId = urlParams.get('id');
        const date = document.getElementById('date').value;
        const outcomeWL = document.getElementById('outcomeWL').value;
        const aspectCheckboxes = document.querySelectorAll('input[name="aspect"]:checked');
        const aspect = Array.from(aspectCheckboxes).map(checkbox => checkbox.value);
        const heroesCheckboxes = document.querySelectorAll('input[name="heroes"]:checked');
        const heroes = Array.from(heroesCheckboxes).map(checkbox => checkbox.value);
        const villain = document.getElementById('villain').value;

        const gameLog = await this.client.updateGameLog(
            gameId,
            date,
            outcomeWL,
            aspect,
            heroes,
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