import {getParameterByName, setTextNode} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');
    displayMilitaryUnit()

    infoForm.addEventListener('submit', event => addInfoAction(event));
});

/**
 * Action event handled for updating soldier info.
 * @param {Event} event dom event
 */
function addInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 201) {
            window.location.href = "../militaryUnit_view/militaryUnit_view.html?militaryUnit=" + getParameterByName("militaryUnit");
        }
    };
    xhttp.open("POST", getBackendUrl() + '/api/soldiers', true);

    const request = {
        'militaryUnit': getParameterByName("militaryUnit"),
        'name': document.getElementById('name').value,
        'rank': document.getElementById('rank').value,
        'age': parseInt(document.getElementById('age').value)
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
}

function displayMilitaryUnit() {
    setTextNode("militaryUnit", "Military Unit: " + getParameterByName("militaryUnit"));
}

