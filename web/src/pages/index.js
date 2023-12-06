import McTrackerClient from '../api/mcTrackerClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";


class Homepage extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'setupModalLogic', 'showModal'], this);

        this.header = new Header(this.dataStore);
        console.log("Homepage constructor");
    }

    mount() {
        this.header.addHeaderToPage();

        this.client = new McTrackerClient();

        this.setupModalLogic();

        document.getElementById("openModalButton").addEventListener("click", () => {
            this.showModal();
        });
    }

    setupModalLogic() {
        document.getElementById("closeModal").addEventListener("click", () => {
            this.hideModal();
        });

        window.addEventListener("click", (event) => {
            if (event.target === document.getElementById("myModal")) {
                this.hideModal();
            }
        });
    }

    showModal() {
        document.getElementById("myModal").style.display = "block";
    }

    hideModal() {
        document.getElementById("myModal").style.display = "none";
    }
}

const main = async () => {
    const homePage = new Homepage();
    homePage.mount();
};

window.addEventListener('DOMContentLoaded', main);






