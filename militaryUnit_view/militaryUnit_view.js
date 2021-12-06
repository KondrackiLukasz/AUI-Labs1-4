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
    fetchAndDisplayMilitaryUnit();
    fetchAndDisplaySoldiers();
});

/**
 * Fetches all militaryUnits and modifies the DOM tree in order to display them.
 */
function fetchAndDisplaySoldiers() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displaySoldiers(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/militaryUnits/' + getParameterByName('militaryUnit') + '/soldiers', true);
    xhttp.send();
}

/**
 * Updates the DOM tree in order to display soldiers.
 *
 * @param {{soldiers: {id: number, name:string}[]}} soldiers
 */
function displaySoldiers(soldiers) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    soldiers.soldiers.forEach(soldier => {
        tableBody.appendChild(createTableRow(soldier));
    })
}

/**
 * Creates single table row for entity.
 *
 * @param {{id: number, name: string}} soldier
 * @returns {HTMLTableRowElement}
 */
function createTableRow(soldier) {
    let tr = document.createElement('tr');

    tr.appendChild(createTextCell(soldier.name));
    tr.appendChild(createLinkCell('view', '../soldier_view/soldier_view.html?militaryUnit='
        + getParameterByName('militaryUnit') + '&soldier=' + soldier.id));
    tr.appendChild(createLinkCell('edit', '../soldier_edit/soldier_edit.html?militaryUnit='
        + getParameterByName('militaryUnit') + '&soldier=' + soldier.id));
    tr.appendChild(createButtonCell('delete', () => deleteSoldier(soldier.id)));
    return tr;
}

/**
 * Deletes entity from backend and reloads table.
 *
 * @param {number} soldier to be deleted
 */
function deleteSoldier(soldier) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplaySoldiers();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/soldiers/' + soldier, true);
    xhttp.send();
}


/**
 * Fetches single militaryUnit and modifies the DOM tree in order to display it.
 */
function fetchAndDisplayMilitaryUnit() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayMilitaryUnit(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/militaryUnits/' + getParameterByName('militaryUnit'), true);
    xhttp.send();
}

/**
 * Updates the DOM tree in order to display militaryUnit.
 *
 * @param {{login: string, name: string, surname:string}} militaryUnit
 */
function displayMilitaryUnit(militaryUnit) {
    setTextNode('militaryUnitName', 'Military Unit: ' + militaryUnit.name);
    setTextNode('maxCapacity', militaryUnit.maxCapacity);
    document.getElementById('addSoldier').setAttribute('href', "../add_soldier/add_soldier.html?militaryUnit=" + militaryUnit.name);
}
