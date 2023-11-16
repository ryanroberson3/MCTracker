import McTrackerClient from '../api/mcTrackerClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';
import { formatDateToMMDDYYYY } from '../util/dateUtils';


class ViewAllGameLogs extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'addGameLogsToPage'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.addGameLogsToPage);
        this.header = new Header(this.dataStore);
        console.log("ViewAllGameLogs constructor");
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
        let gameLogHtml = '<table><tr><th>Game Id</th><th>Date</th><th>Outcome</th><th>Aspects</th><th>Heroes</th><th>Villain</th></tr>';

        for (const gameLog of gameLogs) {
            gameLogHtml += `
            <tr>
                <td>
                    <a href="/viewGameLog.html?id=${gameLog.gameId}">${gameLog.gameId}</a>
                </td>
                    <td>${formatDateToMMDDYYYY(gameLog.date)}</td>
                    <td>${gameLog.outcomeWL}</td>
                    <td>${gameLog.aspect}</td>
                    <td>${gameLog.heroes}</td>
                    <td>${gameLog.villain}</td>
                </tr>
            `;
        }
        document.getElementById('gameLogList').innerHTML = gameLogHtml;
    }
}

const main = async () => {
    const viewAllGameLogs = new ViewAllGameLogs();
    viewAllGameLogs.mount();
}
window.addEventListener('DOMContentLoaded', main);