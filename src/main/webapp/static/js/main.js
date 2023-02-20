const selectTag = document.querySelector('#filter');
const categoriesSelectTag = document.querySelector("#categories-filter");
const suppliersSelectTag = document.querySelector("#suppliers-filter");
const cardsContainer = document.querySelector(".row");

const fetchAndBuildCards = async (id) => {
    const response = await fetch(`/api/filter/category?id=${id}`);
    const data = await response.json();
    cardsContainer.replaceChildren();
    data.forEach(record => createCard(record));
}

// <div className="card">
//     <img className="" src="http://placehold.it/400x250/000/fff"
//          th:attr="src='/static/img/product_' + ${prod.id} + '.jpg'" alt=""/>
//     <div className="card-header">
//         <h4 className="card-title" th:text="${prod.name}">Product name</h4>
//         <p className="card-text" th:text="${prod.description}">Product description... </p>
//     </div>
//     <div className="card-body">
//         <div className="card-text">
//             <p className="lead" th:text="${prod.getPrice()}">100 USD</p>
//         </div>
//         <div className="card-text">
//             <a className="btn btn-success" href="#">Add to cart</a>
//         </div>
//     </div>
// </div>

const createCard = (jsonObject) => {
    // cardsContainer.innerHTML = `
    //         <div className="card">
    //             <img src='/static/img/product_${jsonObject.id}.jpg' alt=""/>
    //             <div className="card-header">
    //                 <h4 className="card-title">${jsonObject.name}</h4>
    //                 <p className="card-text">${jsonObject.description}</p>
    //             </div>
    //             <div className="card-body">
    //                 <div className="card-text">
    //                     <p className="lead">${jsonObject.defaultPrice}</p>
    //                 </div>
    //                 <div className="card-text">
    //                     <a className="btn btn-success" href="#">Add to cart</a>
    //                 </div>
    //             </div>
    //         </div>
    // `
    const col = document.createElement('div');
    col.classList.add('col', 'col-sm-12', 'col-md-6', 'col-lg-4');
    cardsContainer.append(col);
    const card = document.createElement('div');
    card.classList.add('card');
    col.append(card);
    const img = document.createElement('img');
    img.src = `/static/img/product_${jsonObject.id}.jpg`;
    const cardHeader = document.createElement('div');
    cardHeader.classList.add('card-header');
    const title = document.createElement('h4');
    title.textContent = jsonObject.name;
    title.classList.add('card-title');
    cardHeader.append(title);
    const description = document.createElement('p');
    description.textContent = jsonObject.description;
    description.classList.add('card-text');
    cardHeader.append(description);
    const cardBody = document.createElement('div');
    cardBody.classList.add('card-body');
    const textInBody = document.createElement('div');
    textInBody.classList.add('card-text');
    const textInBodyTwo = document.createElement('div');
    textInBodyTwo.classList.add('card-text');
    const price = document.createElement('p');
    price.classList.add('lead');
    price.textContent = jsonObject.defaultPrice + " USD";
    textInBody.append(price);
    const cartButton = document.createElement('a');
    cartButton.classList.add('btn', 'btn-success');
    cartButton.href = '#';
    cartButton.textContent = 'Add to cart';
    cartButton.dataset.product = jsonObject.id;
    textInBodyTwo.append(cartButton);
    cardBody.append(textInBody);
    cardBody.append(textInBodyTwo);
    card.append(img);
    card.append(cardHeader);
    card.append(cardBody);
    activateAddCartButton()
}

function initPage() {
    selectTag.addEventListener('change', (e) => handleFilterTypeSelect(e))
    activateAddCartButton();
}

function handleFilterTypeSelect(e) {
    const eventTargetValue = e.target.value;
    selectTag.classList.add("inactive");
    if (eventTargetValue === "category-filter") {
        categoriesSelectTag.classList.remove("inactive");
        handleSelect(categoriesSelectTag);
    } else if (eventTargetValue === "supplier-filter") {
        suppliersSelectTag.classList.remove("inactive");
        handleSelect(suppliersSelectTag);
    }
}

function handleGoBack() {
    selectTag.classList.remove("inactive");
    categoriesSelectTag.classList.add("inactive");
    suppliersSelectTag.classList.add("inactive");
}

function handleSelect(tagType) {
    tagType.addEventListener('change', e => {
        if (e.target.value === 'back') {
            handleGoBack();
        }
        // console.log(e.target.value)
        fetchAndBuildCards(e.target.value);
    })
}

function selectAddCartButtons(){
    return document.querySelectorAll(`.btn.btn-success`);
}




function createEventListener(addCartButtons){
    addCartButtons.forEach((button) => {
        button.addEventListener('click',async (event) => {
            const response = await fetch(`/cart?product=${event.currentTarget.dataset.product}&change=plus`,{
                method: "POST"
            })
            if (response.status != 200){
                alert("Something wrong happened")
            }

        })
    })
}

function activateAddCartButton(){
    createEventListener(selectAddCartButtons());
}


initPage();