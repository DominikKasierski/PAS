function showConfirmEmployment(text, number) {
    let r = confirm(text);
    if (r === true) {
        document.getElementById('EmploymentListForm:j_idt39:' + number + ':hdnBtn').click();
    }
}

function showConfirmBabysitter(text, number) {
    let r = confirm(text);
    if (r === true) {
        document.getElementById('BabysitterListForm:j_idt39:' + number + ':hdnBtn').click();
    }
}

function checkLogin(login, message) {
    let pattern = /^\w{2,}$/
    if (pattern.test(login)) {
        document.getElementById('loginDiv').innerHTML = "";
        return true;
    } else {
        document.getElementById('loginDiv').innerHTML = message;
        return false;
    }
}

function checkName(name, message) {
    let pattern = /^[A-Za-ząćęłńóśżźĄĆĘŁŃÓŚŻŹ]{2,}$/;
    if (pattern.test(name)) {
        document.getElementById('nameDiv').innerHTML = "";
        return true;
    } else {
        document.getElementById('nameDiv').innerHTML = message;
        return false;
    }
}

function checkSurname(surname, message) {
    let pattern = /^[A-Za-ząćęłńóśżźĄĆĘŁŃÓŚŻŹ]{2,}$/;
    if (pattern.test(surname)) {
        document.getElementById('surnameDiv').innerHTML = "";
        return true;
    } else {
        document.getElementById('surnameDiv').innerHTML = message;
        return false;
    }
}

function checkNumber(number, message, outputDiv, min, max) {
    let isNumber = /^\d+$/.test(number);
    let isInRange = false;
    if (isNumber) {
        let parseToInt = parseInt(number);
        isInRange = parseToInt > min && parseToInt < max
    }

    if (isNumber && isInRange) {
        document.getElementById(outputDiv).innerHTML = "";
        return true;
    } else {
        document.getElementById(outputDiv).innerHTML = message;
        return false;
    }
}

function checkAdminSuperUser(msgLogin, msgName, msgSurname) {
    let loginCheck = checkLogin(document.getElementById('j_idt37:login').value, msgLogin);
    let nameCheck = checkName(document.getElementById('j_idt37:name').value, msgName);
    let surnameCheck = checkSurname(document.getElementById('j_idt37:surname').value, msgSurname);
    return (loginCheck && nameCheck && surnameCheck);
}

function checkClient(msgLogin, msgName, msgSurname, msgNumOfChildren, msgAgeOfTheYoungestChild) {
    let loginNameSurnameCheck = checkAdminSuperUser(msgLogin, msgName, msgSurname);
    let numberOfChildrenCheck = checkNumber(document.getElementById('j_idt37:numberOfChildren').value, msgNumOfChildren, 'numberDiv', 0, 15);
    let ageCheck = checkNumber(document.getElementById('j_idt37:ageOfTheYoungestChild').value, msgAgeOfTheYoungestChild, 'ageDiv', 0, 15);
    return (loginNameSurnameCheck && numberOfChildrenCheck && ageCheck);
}

function checkBabysitter(msgName, msgSurname, msgBasePrice, msgMinChildAge, msgMaxNumOfChildren) {
    let nameCheck = checkName(document.getElementById('j_idt37:name').value, msgName);
    let surnameCheck = checkSurname(document.getElementById('j_idt37:surname').value, msgSurname);
    let basePriceCheck = checkNumber(document.getElementById('j_idt37:basePriceForHour').value, msgBasePrice, 'basePriceDiv', 0, 1000);
    let childAgeCheck = checkNumber(document.getElementById('j_idt37:minChildAge').value, msgMinChildAge, 'minChildAgeDiv', 0, 15);
    let numChildrenCheck = checkNumber(document.getElementById('j_idt37:maximalNumberOfChildren').value, msgMaxNumOfChildren, 'maxNumOfChildDiv', 0, 15);
    return (nameCheck && surnameCheck && basePriceCheck && childAgeCheck && numChildrenCheck);
}

function checkTeachingBabysitter(msgName, msgSurname, msgBasePrice, msgMinChildAge, msgMaxNumOfChildren, msgYearsOfExp) {
    let basicPropCheck = checkBabysitter(msgName, msgSurname, msgBasePrice, msgMinChildAge, msgMaxNumOfChildren);
    let yearsOfExpCheck = checkNumber(document.getElementById('j_idt37:yearsOfExperience').value, msgYearsOfExp, 'yearsDiv', 0, 70);
    return (basicPropCheck && yearsOfExpCheck);
}

function checkTidingBabysitter(msgName, msgSurname, msgBasePrice, msgMinChildAge, msgMaxNumOfChildren, msgValueOfEquip) {
    let basicPropCheck = checkBabysitter(msgName, msgSurname, msgBasePrice, msgMinChildAge, msgMaxNumOfChildren);
    let valueOfEquipCheck = checkNumber(document.getElementById('j_idt37:valueOfCleaningEquipment').value, msgValueOfEquip, 'cleaningDiv', 0, 20000);
    return (basicPropCheck && valueOfEquipCheck);
}

window.onload = function () {
    let selection = document.getElementsByName('BabysitterListForm:j_idt39:j_idt42')[0];
    let tableRows = document.querySelectorAll('table tr');
    tableRows = Array.from(tableRows).slice(1);
    console.log(selection.value);
    if (selection) {
        selection.addEventListener('change', function () {
            let selectionVal = selection.value;
            console.log(selectionVal);
            if (selectionVal === 0) {

                tableRows.show();
            } else {
                tableRows.forEach((row) => {
                    row.querySelector('td').textContent.trim() === selectionVal ?
                        row.style.display = 'table-row' : row.style.display = 'none';
                });
            }
        });
    }
}