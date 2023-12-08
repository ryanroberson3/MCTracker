import McTrackerClient from '../api/mcTrackerClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';
import { formatDateToMMDDYYYY } from '../util/dateUtils';


class ViewAllGameLogs extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'addGameLogsToPage', 'previousPage', 'nextPage'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.addGameLogsToPage);
        this.header = new Header(this.dataStore);
        console.log("ViewAllGameLogs constructor");

        this.currentPage = 1;
        this.logsPerPage = 8;
    }

    async clientLoaded() {
        const gameLogs = await this.client.viewAllGameLogs();
        this.dataStore.set('gameLogs', gameLogs);
    }

    mount() {
        this.header.addHeaderToPage();
        this.client = new McTrackerClient();
        this.clientLoaded();
    }


    addGameLogsToPage() {
        const gameLogs = this.dataStore.get('gameLogs');

        if (gameLogs == null) {
            return;
        }

        const startIndex = (this.currentPage - 1) * this.logsPerPage;
        const endIndex = startIndex + this.logsPerPage;
        const currentGameLogs = gameLogs.slice(startIndex, endIndex);

        let gameLogHtml = '<table><tr><th>Game Id</th><th>Date</th><th>Outcome</th><th>Aspects</th><th>Heroes</th><th>Villain</th></tr>';

        for (const gameLog of currentGameLogs) {
            gameLogHtml += `
            <tr>
                <td>
                    <a href="/viewGameLog.html?id=${gameLog.gameId}">${gameLog.gameId}</a>
                </td>
                    <td>${formatDateToMMDDYYYY(gameLog.date)}</td>
                    <td>${gameLog.outcomeWL}</td>
                    <td>${gameLog.aspect.join(', ')}</td>
                    <td>${gameLog.heroes.join(', ')}</td>
                    <td>${gameLog.villain}</td>
                </tr>
            `;
        }
        
        const previousButton = document.getElementById('previousButton');
        const nextButton = document.getElementById('nextButton');
        const totalPages = Math.ceil(gameLogs.length / this.logsPerPage);

        if (this.currentPage === 1) {
            previousButton.disabled = true;
        } else {
            previousButton.disabled = false;
        }
    
        if (this.currentPage === totalPages) {
            nextButton.disabled = true;
        } else {
            nextButton.disabled = false;
        }

        document.getElementById('gameLogList').innerHTML = gameLogHtml;
        document.getElementById('currentPage').innerText = this.currentPage;
    }

    previousPage() {
        if (this.currentPage > 1) {
            this.currentPage--;
            this.addGameLogsToPage();
        }
    }

    nextPage() {
        const gameLogs = this.dataStore.get('gameLogs');
        const totalPages = Math.ceil(gameLogs.length / this.logsPerPage);

        if (this.currentPage < totalPages) {
            this.currentPage++;
            this.addGameLogsToPage();
        }
    }
}


const main = async () => {
    window.viewAllGameLogs = new ViewAllGameLogs();
    window.viewAllGameLogs.mount();
}
window.addEventListener('DOMContentLoaded', main);