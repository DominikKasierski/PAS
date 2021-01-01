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
    // TODO: SPRAWDZIC CZY NIE JEST PUSTY, SPRAWDZIC CZY MA MINIMUM 2 ZNAKI
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
    // TODO:SPRAWDZIC CZY NIE JEST PUSTY, SPRAWDZIC CZY MA MINIMUM 2 ZNAKI
    let notEmpty = name !== '';
    let minLength = name.length > 2;
    if (notEmpty && minLength) {
        document.getElementById('nameDiv').innerHTML = "";
        return true;
    } else {
        document.getElementById('nameDiv').innerHTML = message;
        return false;
    }
}

function checkSurname(surname, message) {
    // TODO:SPRAWDZIC CZY NIE JEST PUSTY, SPRAWDZIC CZY MA MINIMUM 2 ZNAKI
    let notEmpty = surname !== '';
    let minLength = surname.length > 2;
    if (notEmpty && minLength) {
        document.getElementById('surnameDiv').innerHTML = "";
        return true;
    } else {
        document.getElementById('surnameDiv').innerHTML = message;
        return false;
    }
}

function checkNumberOfChildren(numberOfChildren, message) {
    // TODO:SPRAWDZIC CZY JEST LICZBA I CZY DODATNIA
    let parseToInt = parseInt(numberOfChildren);
    let isNumber = (typeof(parseToInt) === "number" && !isNaN(parseToInt));
    let isPositive = false;
    if(isNumber) {
        isPositive = parseToInt > 0
    }

    if (isNumber && isPositive) {
        document.getElementById('numberDiv').innerHTML = "";
    } else {
        document.getElementById('numberDiv').innerHTML = message;
        return false;
    }
}

function checkAgeOfTheYoungestChild(age, message) {
    // TODO:SPRAWDZIC CZY JEST LICZBA I CZY Z ZAKRESU 0 - 15
    let parseToInt = parseInt(age); //czy w js czy w javie parsowac
    let isNumber = (typeof(parseToInt) === "number" && !isNaN(parseToInt));
    let isInRange = false;
    if(isNumber) {
        isInRange = parseToInt > 0 && parseToInt < 15
    }

    if (isNumber && isInRange) {
        document.getElementById('ageDiv').innerHTML = "";
    } else {
        document.getElementById('ageDiv').innerHTML = message;
        return false;
    }
}

function checkAdminSuperUser(msgLogin, msgName, msgSurname) {
    let one = checkLogin(document.getElementById('j_idt37:login').value, msgLogin);
    let two = checkName(document.getElementById('j_idt37:name').value, msgName);
    let three = checkSurname(document.getElementById('j_idt37:surname').value, msgSurname);
    return (one && two && three);
    // return (checkLogin(document.getElementById('j_idt37:login').value, 'dupa')
    //     && checkName(document.getElementById('j_idt37:name').value, 'dupa')
    //     && checkSurname(document.getElementById('j_idt37:surname').value, 'dupa')); //jak któryś będzie false to przerywa => dupa sie nie wyswietli
}

function checkClient(msgLogin, msgName, msgSurname, msgNumOfChildren, msgAgeOfTheYoungestChild) {
    let one = checkAdminSuperUser(msgLogin, msgName, msgSurname);
    let two = checkNumberOfChildren(document.getElementById('j_idt37:numberOfChildren').value, msgNumOfChildren);
    let three = checkAgeOfTheYoungestChild(document.getElementById('j_idt37:ageOfTheYoungestChild').value, msgAgeOfTheYoungestChild);
    return (one && two && three);
}
