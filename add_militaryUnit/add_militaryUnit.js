import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');

    infoForm.addEventListener('submit', event => addInfoAction(event));
});

/**
 * Action event handled for updating militaryUnit info.
 * @param {Event} event dom event
 */
function addInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 201) {
            window.location.href = "../militaryUnit_list/unit_list.html";
        }
    };
    xhttp.open("POST", getBackendUrl() + '/api/militaryUnits', true);

    const request = {
        'name': document.getElementById('name').value,
        'maxCapacity': document.getElementById('maxCapacity').value,
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
}


