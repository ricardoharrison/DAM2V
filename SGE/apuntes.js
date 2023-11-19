//declaración de variables
let string = "I am the danger";
let number = 62;
let isBoolean = true;
let array = ["Walter", "Jesse", "Gus", "Hank", "Skyler", "Saul"];
let emptyArray = [];
let object = {
    manufacturer: "Fleetwood",
    model: "Bounder",
    yearOfConstruction: 1986,
    color: "beige"
};
let anotherObject = {
    manufacturer: "Dodge",
    model: "Challenger",
    yearOfConstruction: 2009,
    color: "red"
};
let arrayOfObjects = [object, anotherObject];

//funcionalidades básicas
/* alert("Esto es un mensaje alert");
console.log("Este mensaje se imprime por consola");
console.table(arrayOfObjects);  //imprime tabla por consola
let userInput = prompt("Ventana para introducir texto:")
isUserCultured = confirm("¿Te gusta Breaking Bad?");  //aceptar = true | cancelar = false */

let header = document.getElementById("header");
let italicParagraphs = document.getElementsByClassName("italicParagraphsClass");
let boldParagraphs = document.getElementsByClassName("boldParagraphsClass");
let allParagraphs = document.getElementsByTagName("p");

//funciones con arrays + estilos
Array.from(allParagraphs).forEach(singleParagraph => {
    singleParagraph.style.backgroundColor = "gold"; //color de fondo
});

Array.from(italicParagraphs).forEach(singleItalicParagraph => {
    singleItalicParagraph.style.fontStyle = "italic";   //cursiva
});

Array.from(boldParagraphs).forEach(singleBoldParagraph => {
    singleBoldParagraph.style.fontWeight = "bold";  //negrita
});

array.push("Mike"); //añade un nuevo elemento al array (al final de este)
console.log(array);
array.pop();    //elimina el último elemento del array
console.log(array);
array.splice(2, 3); //empezando en el índice 2, elimina un total de 3 elementos
console.log(array);

//listeners
document.getElementById("heisenberg").addEventListener("click", () => alert("Has hecho clic en la imagen"));

let buttonSubmitAge = document.getElementById("buttonSubmitAge");
let textField = document.getElementById("textField");

textField.addEventListener("input", () => {
    if (textField.value.trim() !== '') {
        buttonSubmitAge.disabled = false;
    } else {
        buttonSubmitAge.disabled = true;
    }
});

buttonSubmitAge.addEventListener("click", () => {
    let textFieldInput = textField.value;
    switch(true){
        case textFieldInput < 0:
            alert("No se puede tener edad negativa");
            break;
        case textFieldInput < 18:
            alert("Eres menor de edad");
            break;
        case textFieldInput < 65:
            alert("Estás en edad de trabajar");
            break;
        case textFieldInput < 120:
            alert("Estás en edad de disfrutar de la jubilación");
            break;
        case textFieldInput <= 969:
            alert("¿Has hallado el Santo Grial?");
            break;
        case textFieldInput > 969:
            alert("Eres más viejo que Matusalén")
            break;
        default:
            alert("Eso no parece un número");
            break;
    }
});

//crear, añadir y borrar elementos
let myDivs = document.getElementsByClassName("child");
let myNewAddChildButton = document.createElement("input");
myNewAddChildButton.value = "Añadir nuevo párrafo";
myNewAddChildButton.type = "button";
let myNewRemoveChildButton = document.createElement("input");
myNewRemoveChildButton.value = "Eliminar párrafo";
myNewRemoveChildButton.type = "button";
myDivs[0].appendChild(myNewAddChildButton);
myDivs[0].appendChild(myNewRemoveChildButton);

myNewAddChildButton.addEventListener("click", () => {
    let newParagraph = document.createElement("p");
    newParagraph.innerHTML = "Esto es un nuevo párrafo";
    myDivs[0].appendChild(newParagraph);
});

myNewRemoveChildButton.addEventListener("click", paragraph => {
    if(myDivs[0].lastChild.tagName.toLowerCase() == "p"){
        myDivs[0].removeChild(myDivs[0].lastChild);
    }
});

//radio buttons + atributo 'checked'
let colores = document.getElementsByName("colores");
let changeColorButton = document.getElementById("changeColor");
changeColorButton.addEventListener("click", () => {
    Array.from(colores).forEach((color) => {
        if(color.checked){
            document.body.style.backgroundColor = color.id;
        }
    });
});

//peticiones API
let buttonPikachu = document.getElementById("pokemonButton");
buttonPikachu.addEventListener("click", () => {
    fetch("https://pokeapi.co/api/v2/pokemon/pikachu")     //petición a la API
        .then(response => response.json())                      //almacenar la respuesta en formato JSON
        .then(data => console.log(data));                       //impresión por consola de los datos recibidos
});



