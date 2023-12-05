import McTrackerClient from '../api/mcTrackerClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";


class Homepage extends BindingClass {
    constructor() {
        super();
        
        this.bindClassMethods(['mount'], this);


        this.header = new Header(this.dataStore);

        console.log("Homepage constructor");
    }

mount() {

        this.header.addHeaderToPage();

        this.client = new McTrackerClient();
    }
}

const main = async () => {
    const homePage = new Homepage();
    homePage.mount();
};

window.addEventListener('DOMContentLoaded', main);

// import McTrackerClient from '../api/mcTrackerClient';
// import Header from '../components/header';
// import BindingClass from "../util/bindingClass";

// class Homepage extends BindingClass {
//     constructor() {
//         super();
//         this.bindClassMethods(['mount', 'setupModalLogic'], this);

//         this.header = new Header(this.dataStore);
//         console.log("Homepage constructor");
//     }

//     mount() {
//         this.header.addHeaderToPage();

//         this.client = new McTrackerClient();

//         // Append modal HTML to the body
//         const modalHtml = `
//             <div id="myModal" class="modal">
//                 <div class="modal-content">
//                     <span class="close" id="closeModal">&times;</span>
//                     <p class="button-group">
//                         <a href="createGameLog.html" class="button2">Create GameLog</a>
//                         <a href="viewAllGameLogs.html" class="button2">View GameLogs</a>
//                         <a href="viewAllStats.html" class="button2">View Stats</a>
//                     </p>
//                 </div>
//             </div>
//         `;
//         document.body.insertAdjacentHTML('beforeend', modalHtml);

//         // Call additional logic needed for the modal
//         this.setupModalLogic();
//     }

//     setupModalLogic() {
//         document.getElementById("closeModal").addEventListener("click", () => {
//             document.getElementById("myModal").style.display = "none";
//         });

//         window.addEventListener("click", (event) => {
//             if (event.target === document.getElementById("myModal")) {
//                 document.getElementById("myModal").style.display = "none";
//             }
//         });
//     }
// }

// const main = async () => {
//     const homePage = new Homepage();
//     homePage.mount();
// };

// window.addEventListener('DOMContentLoaded', main);



