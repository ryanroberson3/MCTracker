import axios from "axios";
import BindingClass from "../util/bindingClass";
import Authenticator from "./authenticator";



export default class McTrackerClient extends BindingClass {

    constructor(props = {}) {
        super();
        const methodsToBind = ['createGameLog'];
        this.bindClassMethods(methodsToBind, this);

        this.authenticator = new Authenticator();;
        this.props = props;

        axios.defaults.baseURL = process.env.API_BASE_URL;
        this.axiosClient = axios;
        this.clientLoaded();
    }

    clientLoaded() {
        if (this.props.hasOwnProperty("onReady")) {
            this.props.onReady(this);
        }
    }


    async getIdentity(errorCallback) {
        try {
            const isLoggedIn = await this.authenticator.isUserLoggedIn();

            if (!isLoggedIn) {
                return undefined;
            }

            return await this.authenticator.getCurrentUserInfo();
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async login() {
        this.authenticator.login();
    }

    async logout() {
        this.authenticator.logout();
    }

    async getTokenOrThrow(unauthenticatedErrorMessage) {
        const isLoggedIn = await this.authenticator.isUserLoggedIn();
        if (!isLoggedIn) {
            throw new Error(unauthenticatedErrorMessage);
        }

        return await this.authenticator.getUserToken();
    }



    async createGameLog(date, aspect, outcomeWL, villain, heroes, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only logged in users can create a game log");
            const response = await this.axiosClient.post(`game_logs`, {
                date: date,
                aspect: aspect,
                outcomeWL: outcomeWL,
                villain: villain,
                heroes: heroes
            }, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.date.gameLog
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }
}