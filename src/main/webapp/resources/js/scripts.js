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
    let notEmpty = login !== '';
    let minLength = login.length > 2;
    if (notEmpty && minLength) {
        document.getElementById('loginDiv').innerHTML = "";
        return true;
    } else {
        document.getElementById('loginDiv').innerHTML = message;
        return false;
    }
}

function checkName(name, message) {
    let notEmpty = name !== '';
    let minLength = name.length > 2;
    let onlyLetters = (/\d/.test(name));
    if (notEmpty && minLength && !onlyLetters) {
        document.getElementById('nameDiv').innerHTML = "";
        return true;
    } else {
        document.getElementById('nameDiv').innerHTML = message;
        return false;
    }
}

function checkSurname(surname, message) {
    let notEmpty = surname !== '';
    let minLength = surname.length > 2;
    let onlyLetters = (/\d/.test(name));
    if (notEmpty && minLength && !onlyLetters) {
        document.getElementById('surnameDiv').innerHTML = "";
        return true;
    } else {
        document.getElementById('surnameDiv').innerHTML = message;
        return false;
    }
}

function checkNumber(number, message, outputDiv) {
    let isNumber = /^\d+$/.test(number);
    let isInRange = false;
    if(isNumber) {
        let parseToInt = parseInt(number);
        isInRange = parseToInt > 0 && parseToInt < 15
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
    let numberOfChildrenCheck = checkNumber(document.getElementById('j_idt37:numberOfChildren').value, msgNumOfChildren, 'numberDiv');
    let ageCheck = checkNumber(document.getElementById('j_idt37:ageOfTheYoungestChild').value, msgAgeOfTheYoungestChild, 'ageDiv');
    return (loginNameSurnameCheck && numberOfChildrenCheck && ageCheck);
}
