import axios from "axios";
import BindingClass from "../util/bindingClass";
import Authenticator from "./authenticator";



export default class McTrackerClient extends BindingClass {

    constructor(props = {}) {
        super();
        const methodsToBind = ['clientLoaded', 'getIdentity', 'login', 'logout','createGameLog', 'viewGameLog',
                                    'viewAllGameLogs', 'updateGameLog'];
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
            return response.data.gameLog
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    
    async viewGameLog(id, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can see a gameLog");
            const response = await this.axiosClient.get(`game_logs/${id}`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            console.log("Received response:", response);
            return response.data.gameLogModel;
        } catch (error) {
            console.error("Error in viewGameLog:", error);
            this.handleError(error, errorCallback)
        }
    }


    async viewAllGameLogs(errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only logged in users can view all their game logs");
            const response = await this.axiosClient.get(`game_logs`,
            {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.gameLogList;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }


    async updateGameLog(gameId, date, outcomeWL, aspect, heroes, villain) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can update a game log");
            const response = await this.axiosClient.put(`game_logs/${gameId}`, {
                gameId: gameId,
                date: date,
                outcomeWL: outcomeWL,
                aspect: aspect,
                heroes: heroes,
                villain: villain,
            }, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.gameLog;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }


    async deleteGameLog(id, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can delete a game log");
            const response = await this.axiosClient.delete(`game_logs/${id}`,
            {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.deleteGameLog;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }


    handleError(error, errorCallback) {
        console.error(error);

        const errorFromApi = error?.response?.data?.error_message;
        if (errorFromApi) {
            console.error(errorFromApi)
            error.message = errorFromApi;
        }

        if (errorCallback) {
            errorCallback(error);
        }
    }
}