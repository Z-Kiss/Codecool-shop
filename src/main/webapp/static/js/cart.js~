function initPage(){
    const button = document.querySelector(`#buy-it-button`)
    button.addEventListener('click',() => alert("Your soul is already MINE"))
    initEventListenerForChangeAmount(selectAllPlusButtons(), 1);
    initEventListenerForChangeAmount(selectAllMinusButtons(), -1);
    setAllPrice();
}
async function changeProductOnServer(prodId, value) {
    const response = await fetch(`/cart?product=${prodId}&change=${value < 1 ? 'minus' : 'plus'}`,{
        method: "POST"
    })
    if (response.status != 200) {
        alert("Something wrong happened")
    }
}
function selectAllMinusButtons(){
    return document.querySelectorAll(`.minus-btn`);
}

function selectAllPlusButtons(){
    return document.querySelectorAll(`.plus-btn`);
}

function setAllPrice(){
    const amountNodes = document.querySelectorAll(`.amount`)
    amountNodes.forEach((node) => {
        calculatePrice(node);
    })
}

function calculatePrice(amountButton){

    const prodId = amountButton.dataset.prodId;
    const prodAmount = parseInt(amountButton.innerText);
    const priceNode = document.querySelector(`.price[data-prod-id="${prodId}"]`);
    let price = parseInt(priceNode.dataset.defaultPrice);
    priceNode.innerText = (price*prodAmount).toString();
}
function initEventListenerForChangeAmount(buttons, value) {
    buttons.forEach((button) => button.addEventListener('click', (event) => {
        const amountNode = event.currentTarget.parentNode.querySelector(`:nth-child(2)`);
        let amount = parseInt(amountNode.innerText) + value;
        if(amount == 0){
            removeNode(amountNode);
        }else{
            amountNode.innerText = amount.toString();
            calculatePrice(amountNode);
        }

        changeProductOnServer(amountNode.dataset.prodId,value)


    }));
}
 function removeNode(amountNode){
     document.querySelector(`.card[data-prod-id="${amountNode.dataset.prodId}"]`).remove();
 }


initPage();