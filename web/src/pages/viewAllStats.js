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

        
    }
}

const main = async () => {
    const viewAllStats = new ViewAllStats();
    viewAllStats.mount();
}
window.addEventListener('DOMContentLoaded', main);
