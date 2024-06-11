const loginForm = document.querySelector(".login"),
    search = document.getElementById("search");
let searchBy = "all";
const closeBtn = document.querySelector(".close-btn"),
    regForm = document.querySelector(".reg-form"),
    submittedForm = document.getElementById("mern"),
    addCustomerBtn = document.querySelector("#add-customer");

let currEditId;



loginForm.addEventListener('submit', (e) => {
    e.preventDefault();
    const email = e.target.username.value;
    const password = e.target.password.value;

    //you have username and Password
    //I'll Get Token First..
    loginUser(email, password);

})

if (sessionStorage.getItem("token")) loginUser();


async function loginUser(email, password) {
    try {


        if (!sessionStorage.getItem("token")) {

            //You have a token in 
            const res = await fetch(`http://localhost:8080/auth/login`, {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json',

                },
                body: JSON.stringify({
                    email: email,
                    password: password
                })
            });

            const data = await res.json();
            sessionStorage.setItem("token", data.jwtToken);
        }

        getData();
        loginForm.style.display = "none";
        document.querySelector(".customer-list-screen").style.display = "block";

    } catch (error) {
        alert("Something Went Wrong");
    }
}

search.addEventListener('change', (e) => {
    // console.log(e.target.value);
    //It is Correctly Implemented...
    searchBy = e.target.value;

});



const faSearch = document.querySelector(".fa-search");


faSearch.addEventListener('click', (e) => {
    //let's extract the input..
    const input = document.getElementById("input")
    let value = input.value;
    console.log(searchBy, value);
    getData(searchBy, value.trim());
})

async function getData(type, value) {
    try {

        let token = sessionStorage.getItem("token")
        console.log("Bearer " + token);
        const res = await fetch(`http://localhost:8080/user/find-by-search/${type}?value=${value}`, {
            method: "GET",
            headers: {
                "Authorization": `Bearer ${token}`,
                'Content-Type': 'application/json',
            }
        });
        let data = await res.json();

        data = Object.keys(data).map(key => data[key]);

        //let's have the 
        const tbody = document.getElementById("tbody");
        tbody.innerHTML = "";
        data.forEach(ele => {
            getRow(ele, tbody);
        });

    } catch (error) {
        console.log(error);
    }
}


function getRow(ele, tbody) {

    tbody.innerHTML +=
        `<tr id="${ele.id}">
        <td>${ele.firstName}</td>
        <td>${ele.lastName}</td>
        <td>${ele.address}</td>
        <td>${ele.city}</td>
        <td>${ele.state}</td>
        <td>${ele.email}</td>
        <td>${ele.phone}</td>
        <td>
            <i class="fa fa-trash" style="margin-inline:15px" onclick='removeRow(${ele.id})'></i>
            <i class="fa fa-edit" onclick='editUser(${ele.id})'></i>
        </td>
</tr>
`;
}

async function removeRow(id) {

    try {

        const token = sessionStorage.getItem("token");
        const res = await fetch(`http://localhost:8080/user/delete?id=${id}`, {
            method: 'DELETE',
            headers: {

                "Authorization": `Bearer ${token}`,
                'Content-Type': 'application/json',

            }
        });

        alert("Deleted Successfully");
        const row = document.getElementById(id);
        row.parentNode.removeChild(row);

    } catch (error) {
        console.log(error);
    }
}

//on clicking the close btn..

closeBtn.addEventListener('click', () => {
    regForm.style.display = "none";
    submitBtn.style.display = "flex";
    updateUserBtn.style.display = "none";
});

addCustomerBtn.addEventListener('click', () => {
    regForm.style.display = "flex";
    submittedForm.reset();
});

const submitBtn = document.querySelector(".submit-btn");

submittedForm.addEventListener('submit', (e) => {
    e.preventDefault();

    let user = {
        "firstName": e.target.first.value,
        "lastName": e.target.last.value,
        "street": e.target.street.value,
        "address": e.target.address.value,
        "city": e.target.city.value,
        "state": e.target.state.value,
        "email": e.target.email.value,
        "phone": e.target.phone.value
    };

    console.log(user);

    //I'll have to sent the Post Request... here we go...
    addUser(user);
    submittedForm.reset();
    closeBtn.click();


});

async function addUser(user) {
    try {
        const token = sessionStorage.getItem("token");
        const res = await fetch(`http://localhost:8080/user/add`, {
            method: "POST",
            headers: {
                "Authorization": `Bearer ${token}`,
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(user)
        });

        const data = await res.json();

        const tbody = document.getElementById("tbody");

        getRow(data, tbody);
        console.log(data);
        alert(data["message"]);

    } catch (error) {
        alert("Updated User");
    }

}

const updateUserBtn = document.querySelector(".update");
const addUserBtn = document.querySelector(".add");

async function editUser(userId) {
    const token = sessionStorage.getItem("token");
    const res = await fetch(`http://localhost:8080/user/find-by-id?id=${userId}`, {
        method: "GET",
        headers: {
            "Authorization": `Bearer ${token}`,
            'Content-Type': 'application/json',
        }
    });

    const data = await res.json();

    console.log(data);

    currEditId = userId;

    let firstName = submittedForm.querySelector("#first");
    let lastName = submittedForm.querySelector("#last");
    let street = submittedForm.querySelector("#street");
    let city = submittedForm.querySelector("#city");
    let address = submittedForm.querySelector("#address");
    let state = submittedForm.querySelector("#state");
    let email = submittedForm.querySelector("#email");
    let phone = submittedForm.querySelector("#phone");

    firstName.value = data.firstName;
    lastName.value = data.lastName;
    street.value = data.street;
    city.value = data.city;
    address.value = data.address;
    state.value = data.state;
    email.value = data.email;
    phone.value = data.phone;

    regForm.style.display = "flex";

    updateUserBtn.style.display = "flex";
    addUserBtn.style.display = "none";

}

updateUserBtn.addEventListener('click', (e) => {
    e.preventDefault();
    let user = {
        "firstName": submittedForm.first.value,
        "lastName": submittedForm.last.value,
        "street": submittedForm.street.value,
        "address": submittedForm.address.value,
        "city": submittedForm.city.value,
        "state": submittedForm.state.value,
        "email": submittedForm.email.value,
        "phone": submittedForm.phone.value
    };

    updateUser(user, currEditId);

    updateUserBtn.style.display = "none";
    addUserBtn.style.display = "flex";
    closeBtn.click();
});

async function updateUser(user, id) {
    try {
        const token = sessionStorage.getItem("token");
        const res = await fetch(`http://localhost:8080/user/update?id=${id}`, {
            method: "PUT",
            headers: {
                "Authorization": `Bearer ${token}`,
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(user)
        });

        const data = await res.json();
        alert(data.message);

        let row = document.getElementById(`${id}`);

        row.innerHTML = `        
        <td>${data.firstName}</td>
        <td>${data.lastName}</td>
        <td>${data.address}</td>
        <td>${data.city}</td>
        <td>${data.state}</td>
        <td>${data.email}</td>
        <td>${data.phone}</td>
        <td>
            <i class="fa fa-trash" style="margin-inline:15px" onclick='removeRow(${data.id})'></i>
            <i class="fa fa-edit" onclick='editUser(${data.id})'></i>
        </td>
        `;


    } catch (error) {
        console.log(error);
    }

}


//phase2 I'm Doing 

const syncBtn = document.getElementById("sync");
syncBtn.addEventListener('click', (e) => {
    //I've to sync the Data... 
    //you have to do some authentications.. here we go..
    if (!sessionStorage.getItem("data")) getToken();
    else getCustomerList()

});


async function getToken() {
    try {

        const token = sessionStorage.getItem("token");

        const resp = await fetch(`http://localhost:8080/sunbase/token`, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}` // Include the access token here
            },
            body: JSON.stringify({
                "login_id": "test@sunbasedata.com",
                "password": "Test@123"
            })
        });

        console.log(resp);

        const data = await resp.json();

        console.log(data.access_token);
        sessionStorage.setItem("data", data.access_token);
        getCustomerList(data);
        return data;
    } catch (error) {
        console.error('Error:', error);

    }
}

async function getCustomerList(data) {


    try {
        const res = await fetch(`http://localhost:8080/sunbase/customer-list`, {
            method: "GET",
            headers: {
                "Authorization": `Bearer ${sessionStorage.getItem("data")}`,
                'Content-Type': 'application/json'
            },
        });

        const resData = await res.json();

        let arr = Object.keys(resData).map(key => resData[key]);
        arr.forEach(async (ele) => {
            let user = {
                "firstName": ele.first_name,
                "lastName": ele.last_name,
                "street": ele.street,
                "address": ele.address,
                "city": ele.city,
                "state": ele.state,
                "email": ele.email,
                "phone": ele.phone
            };



            const userCheck = await fetch(`http://localhost:8080/user/find-by-search/phone?value=${user.phone}`, {
                method: "GET",
                headers: {
                    "Authorization": `Bearer ${sessionStorage.getItem("token")}`,
                    'Content-Type': 'application/json',
                }
            });

            let searchData = await userCheck.json();
            searchData = searchData[0];
            console.log(searchData);
            if (!searchData)
                addUser(user);
            else updateUser(user, searchData.id);
        });

    } catch (error) {
        console.log(error);
    }
}








