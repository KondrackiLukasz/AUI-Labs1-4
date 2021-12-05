import {
    getParameterByName,
    clearElementChildren,
    createLinkCell,
    createButtonCell,
    createTextCell,
    createImageCell,
    setTextNode
} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplaySoldier();
});

/**
 * Fetches single soldier and modifies the DOM tree in order to display it.
 */
function fetchAndDisplaySoldier() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displaySoldier(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/soldiers/' + getParameterByName('soldier'), true);
    xhttp.send();
}

/**
 * Updates the DOM tree in order to display soldier.
 *
 * @param {{login: string, name: string, surname:string}} soldier
 */
function displaySoldier(soldier) {
    setTextNode('soldierName', soldier.name);
    setTextNode('rank', soldier.rank);
    setTextNode('age', soldier.age);
    setTextNode('militaryUnit', soldier.militaryUnit);
}
