import McTrackerClient from '../api/mcTrackerClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';
import { formatDateToMMDDYYYY } from '../util/dateUtils';


class ViewGameLog extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'addGameLogToPage', 'redirectToUpdateGameLog', 'redirectToDeleteGameLog'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.addGameLogToPage);

        this.header = new Header(this.dataStore);
        console.log("ViewGameLog constructor");
    }


    async clientLoaded() {
        const urlParams = new URLSearchParams(window.location.search);
        const gameId = urlParams.get('id');

        const gameLog = await this.client.viewGameLog(gameId);
        this.dataStore.set('gameLog', gameLog);
    }


    mount() {
        this.header.addHeaderToPage();
        this.client = new McTrackerClient();
        this.clientLoaded();
        document.getElementById('updateGameLogButton').addEventListener('click', this.redirectToUpdateGameLog);
        document.getElementById('deleteGameLogButton').addEventListener('click', this.redirectToDeleteGameLog);
    }


    addGameLogToPage() {
        const gameLog = this.dataStore.get('gameLog');
        if (gameLog == null) {
            return;
        }
        
        document.getElementById('game-id').innerText = gameLog.gameId;
        console.log("Game Id : " + gameLog.gameId);
        document.getElementById("email").innerText = gameLog.email;
        console.log("Email : " + gameLog.email);
        document.getElementById('date').innerText = formatDateToMMDDYYYY(gameLog.date);
        document.getElementById('villain').innerText = gameLog.villain;
        document.getElementById('outcomeWL').innerText = gameLog.outcomeWL;
        let heroListHtml = '';
        let heroList;
        for (heroList of gameLog.heroes) {
            heroListHtml += '<div class="heroes">' + heroList + '</div>';
        }
        document.getElementById('heroes').innerHTML = heroListHtml;
        let aspectListHtml = '';
        let aspectList;
        for (aspectList of gameLog.aspect) {
            aspectListHtml += '<div class="aspect">' + aspectList + '</div>';
        }
        document.getElementById('aspect-list').innerHTML = aspectListHtml;
    }

    
    redirectToUpdateGameLog() {
        const gameLog = this.dataStore.get('gameLog');
        if (gameLog != null) {
            window.location.href = `/updateGameLog.html?id=${gameLog.gameId}`;
        }
    }


    redirectToDeleteGameLog() {
        const gameLog = this.dataStore.get('gameLog');
        const deleteButton = document.getElementById('deleteGameLogButton');
        const messageContainer = document.getElementById('messageContainer');
        const deleteMessage = document.getElementById('message');

        this.client.deleteGameLog(gameLog.gameId);

        deleteButton.style.display = 'none';
        messageContainer.style.display = 'block';

        deleteMessage.textContent = 'GameLog has been deleted';

        // setTimeout(() => {
        //     window.location.href = `/reservationOptions.html`;
        //   }, 3000); //3000 milli = 3 sec
    }
}

const main = async () => {
    const viewGameLog = new ViewGameLog();
    viewGameLog.mount();
};

window.addEventListener('DOMContentLoaded', main);