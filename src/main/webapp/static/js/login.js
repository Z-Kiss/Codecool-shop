function putModal(root, modalType){
    root.innerHTML = `
        <div id="myModal" class="modal">
            <div class="modal-content">
                <div class="modal-header">
                    <h2>${modalType}</h2>
                    <span id="close">&times;</span>
                </div>               
                <div class="modal-body">
                    <div>
                        <label for="user-email">Email:</label>
                        <br>
                        <input id="user-email" type="email">
                    </div>
                    <div>
                        <label for="user-password">Password:</label>
                        <br>
                        <input id="user-password" type="password">
                    </div>
                    <div>
                        <br>
                        <button id="send-button">OK</button>
                    </div>                                      
                </div>                
                <div class="modal-footer">                
                </div>
            </div>
        </div>
        `
    if (modalType === "Register"){
        generateUserNameInput(document.querySelector(`.modal-body`))
    }
}
function generateUserNameInput(modalBody){
    let userName = document.createElement("div");
    userName.innerHTML = `<label for="user-name">Name:</label>
                                        <br>
                            <input id="user-name" type="text">`

    modalBody.insertAdjacentElement("afterbegin",userName)
}
function initCloseButton(){
    let close = document.querySelector(`#close`);
    close.onclick = function (){
        document.querySelector(`#myModal`).remove();
    }
}
function initSendButton(url){
    let sendButton = document.querySelector(`#send-button`);

    sendButton.onclick = async function () {
        const response = await fetch(url, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(getValues())
        })

        document.querySelector(`#myModal`).remove();
        if (response.status === 200){
            alert("Request accomplished")

        } else{
            alert("Something went Wrong")
        }
        window.location.href = "/";
    }
}

function getValues(){
    const userField = document.querySelector(`#user-name`);
    const passwordField = document.querySelector(`#user-password`)
    const emailField = document.querySelector(`#user-email`)
    if(userField == null){
        return {
            password: passwordField.value,
            email: emailField.value
        }
    }else{
        return {
            name: userField.value,
            password: passwordField.value,
            email: emailField.value
        }
    }
}

function initPage(){
    const root = document.querySelector(`#root`);
    const registerBtn = document.querySelector(`#reg-button`);
    const loginBtn = document.querySelector(`#login-button`);
    const logoutBtn = document.querySelector(`#logout-button`)
    if(registerBtn != null){
        registerBtn.onclick = function (){
            putModal(root,"Register","/register");
            initCloseButton();
            initSendButton("/register");
        }
    }

    if(loginBtn != null){
        loginBtn.onclick = function (){
            putModal(root,"Login", "/login")
            initCloseButton();
            initSendButton("/login");
        }
    }

    if(logoutBtn != null){
        logoutBtn.onclick = async function () {
            const response = await fetch("/logout")
            if(response.status == 200){
                alert("You logged out!");
                window.location.href = "/";
            }
        }
    }
}

initPage()