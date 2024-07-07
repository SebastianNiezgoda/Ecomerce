//API CONTEXT
const getProducts = () => {
    return fetch("/api/products")
        .then(response => response.json());
}

const getCurrentOffer = () => {
    return fetch("/api/current-offer")
        .then(response => response.json());
}

const addProductToCart = (productId) => {
    return fetch(`/api/add-product/${productId}`,{
        method:'POST'
    });
}
const acceptOffer = (acceptOfferRequest) => {
    return fetch(`/api/accept-offer`, {
        method: 'POST',
        headers: {
            "Content-Type":"application/json"
        },
        body: JSON.stringify(acceptOfferRequest)
    }).then(response => response.json());
}

const refreshOffer = (offer) => {
    const offerTotalEl = document.querySelector("#offerTotal");
    const offerItemsCountEl = document.querySelector("#offerItemsCount");

    offerTotalEl.textContent = `${offer.total} PLN`;
    offerItemsCountEl.textContent = `${offer.itemsCount} Items`;
}

const createProductHtmlEl = (productData) => {
    const template = `
        <div class="produkty">
            <h4>${productData.name}</h4>
            <img src="https://gamehag.com/img/rewards/logo/250%20smoczych%20monet.png"  />
            <br><br>
            <span>${productData.price}</span><br>
            <button data-id="${productData.id}">Add to cart</button>       
        </div>
        
        `;

    const htmlEl = document.createElement("li");
    htmlEl.innerHTML = template.trim();
    return htmlEl;
}
const refreshCurrentOffer = () => {
    const totalEl = document.querySelector('#offerTotal');
    const itemCountEl = document.querySelector('#offerItemsCount');

    getCurrentOffer()
        .then(offer => {
            totalEl.textContent = `${offer.total} PLN`;
            itemCountEl.textContent = `${offer.itemsCount} Items`;
        })
}

const initializeCartHandler = (productHtmlEl) => {
    const addToCartBtn = productHtmlEl.querySelector("button[data-id]");
    addToCartBtn.addEventListener("click", (event) => {
        const productId = event.target.getAttribute("data-id");
        addProductToCart(productId)
            .then(() => refreshCurrentOffer());
    });
    return productHtmlEl;
}

const checkoutFormEl = document.querySelector('#checkout');
checkoutFormEl.addEventListener("submit", (event) => {
    event.preventDefault();

    const acceptOfferRequest = {
        firstName: checkoutFormEl.querySelector('input[name="firstname"]').value,
        lastName: checkoutFormEl.querySelector('input[name="lastname"]').value,
        email: checkoutFormEl.querySelector('input[name="email"]').value,
    }

    acceptOffer(acceptOfferRequest)
        .then(reservationDetails => window.location.href = reservationDetails.paymentUrl)
});

//product as json -> product as html -> add to html list
document.addEventListener("DOMContentLoaded", () => {
    console.log("it works");
    const productsList = document.querySelector("#productsList");
    getProducts()
        .then(productsAsJson => productsAsJson.map(createProductHtmlEl))
        .then(productsHtmls => productsHtmls.map(initializeCartHandler))
        .then(productsHtmls => {
            productsHtmls.forEach(htmlEl => productsList.appendChild(htmlEl))
        });

    getCurrentOffer()
        .then(offer => refreshOffer(offer));
});