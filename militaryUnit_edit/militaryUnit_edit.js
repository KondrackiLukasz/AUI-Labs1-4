import {getParameterByName, setTextNode} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');

    infoForm.addEventListener('submit', event => updateInfoAction(event));

    fetchAndDisplayMilitaryUnit();
});

/**
 * Fetches currently logged militaryUnit's militaryUnits and updates edit form.
 */
function fetchAndDisplayMilitaryUnit() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let response = JSON.parse(this.responseText);
            console.log(response);
            for (const [key, value] of Object.entries(response)) {
                let input = document.getElementById(key);
                if (input) {
                    input.value = value;
                }
            }
            setTextNode('militaryUnitName', 'Name: ' + response.name);
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/militaryUnits/' + getParameterByName('militaryUnit'), true);
    xhttp.send();
}

/**
 * Action event handled for updating militaryUnit info.
 * @param {Event} event dom event
 */
function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            fetchAndDisplayMilitaryUnit();
        }
    };
    xhttp.open("PUT", getBackendUrl() + '/api/militaryUnits/' + getParameterByName('militaryUnit'), true);

    const request = {
        'maxCapacity': document.getElementById('maxCapacity').value
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
}

