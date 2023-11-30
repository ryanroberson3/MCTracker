import McTrackerClient from '../api/mcTrackerClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';


class ViewAllStats extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'addStats'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.addStats);
        this.header = new Header(this.dataStore);
    }

    async clientLoaded() {
        const stats = await this.client.viewAllStats();
        this.dataStore.set('stats', stats);
    }

    mount() {
        this.header.addHeaderToPage();
        this.client = new McTrackerClient();
        this.clientLoaded();
    }

    addStats() {
        const stats = this.dataStore.get('stats');
        document.getElementById('winStat').innerText = stats.winStat;
        document.getElementById('lostToSchemeStat').innerText = stats.lostBySchemeStat;
        document.getElementById('lostToDamageStat').innerText = stats.lostByDamageStat;

        document.getElementById('winStat').style.display = 'none';
        document.getElementById('lostToSchemeStat').style.display = 'none';
        document.getElementById('lostToDamageStat').style.display = 'none';

        var xValues = ["Win", "Lost by scheme", "Lost by damage"];
        var yValues = [stats.winStat.toFixed(1), stats.lostBySchemeStat.toFixed(1), stats.lostByDamageStat.toFixed(1)];
        var barColors = ["#1AABF3", "#F0F306", "#E31507"];


        new Chart("myChart", {
            type: "pie",
            data: {
                labels: xValues,
                datasets: [{
                backgroundColor: barColors,
                    data: yValues
                }]
            },
            options: {
                title: {
                    display: true,
                    text: "Stats are shown in percentages: Hover to see!",
                    fontSize: 25
                },
                legend: {
                    display: true,
                    position: 'right',
                    labels: {
                        fontSize: 20
                    }
                }
            }
        });
    }
}

const main = async () => {
    const viewAllStats = new ViewAllStats();
    viewAllStats.mount();
}
window.addEventListener('DOMContentLoaded', main);
