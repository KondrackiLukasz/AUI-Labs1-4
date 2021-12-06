import {
    clearElementChildren,
    createLinkCell,
    createButtonCell,
    createTextCell,
    getParameterByName
} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayMilitaryUnits();
});

/**
 * Fetches all militaryUnits and modifies the DOM tree in order to display them.
 */
function fetchAndDisplayMilitaryUnits() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        console.log(this.status);
        if (this.readyState === 4 && this.status === 200) {
            displayMilitaryUnits(JSON.parse(this.responseText));
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/militaryUnits', true);
    xhttp.send();
}

/**
 * Updates the DOM tree in order to display militaryUnits.
 *
 * @param {{militaryUnits: string[]}} militaryUnits
 */
function displayMilitaryUnits(militaryUnits) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    militaryUnits.militaryUnits.forEach(militaryUnit => {
        tableBody.appendChild(createTableRow(militaryUnit.name));
    })
}

/**
 * Creates single table row for entity.
 *
 * @param {string} militaryUnit
 * @returns {HTMLTableRowElement}
 */
function createTableRow(militaryUnit) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(militaryUnit));
    tr.appendChild(createLinkCell('view', '../militaryUnit_view/militaryUnit_view.html?militaryUnit=' + militaryUnit));
    tr.appendChild(createLinkCell('edit', '../militaryUnit_edit/militaryUnit_edit.html?militaryUnit=' + militaryUnit));
    tr.appendChild(createButtonCell('delete', () => deleteMilitaryUnit(militaryUnit)));
    return tr;
}

/**
 * Deletes entity from backend and reloads table.
 *
 * @param {string } militaryUnit to be deleted
 */
function deleteMilitaryUnit(militaryUnit) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayMilitaryUnits();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/militaryUnits/' + militaryUnit, true);
    xhttp.send();
}
