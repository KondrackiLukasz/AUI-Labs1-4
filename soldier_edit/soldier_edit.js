import {getParameterByName} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');

    infoForm.addEventListener('submit', event => updateInfoAction(event));

    fetchAndDisplaySoldier();
});

/**
 * Fetches currently logged militaryUnit's soldiers and updates edit form.
 */
function fetchAndDisplaySoldier() {
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
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/soldiers/' + getParameterByName('soldier'), true);
    xhttp.send();
}

/**
 * Action event handled for updating soldier info.
 * @param {Event} event dom event
 */
function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            fetchAndDisplaySoldier();
        }
    };
    xhttp.open("PUT", getBackendUrl() + '/api/soldiers/' + getParameterByName('soldier'), true);

    const request = {
        'name': document.getElementById('name').value,
        'rank': document.getElementById('rank').value,
        'age': parseInt(document.getElementById('age').value)
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
}

