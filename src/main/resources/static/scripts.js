function login() {
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    fetch('/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `username=${username}&password=${password}`,
    })
        .then(response => {
            if (response.ok) {
                document.getElementById('login-form').style.display = 'none';
                document.getElementById('endpoints').style.display = 'block';
                document.getElementById('response').innerText = 'Login successful!';
            } else {
                document.getElementById('response').innerText = 'Login failed!';
            }
        });
}

function accessPublicEndpoint() {
    fetch('/public')
        .then(response => response.text())
        .then(data => {
            document.getElementById('response').innerText = `Public Endpoint: ${data}`;
        });
}

function accessPrivateEndpoint() {
    fetch('/private')
        .then(response => {
            if (response.ok) {
                return response.text();
            } else {
                throw new Error('Unauthorized');
            }
        })
        .then(data => {
            document.getElementById('response').innerText = `Private Endpoint: ${data}`;
        })
        .catch(error => {
            document.getElementById('response').innerText = 'Access to private endpoint failed: ' + error.message;
        });
}

// Function to retrieve all users
function getAllUsers() {
    fetch('/public/users')
        .then(response => response.json())
        .then(data => {
            document.getElementById('response').innerText = `All Users: ${JSON.stringify(data)}`;
        })
        .catch(error => console.error('Error:', error));
}

// Function to retrieve a user by ID
function getUserById(id) {
    fetch(`/public/users/${id}`)
        .then(response => response.json())
        .then(data => {
            document.getElementById('response').innerText = `User ${id}: ${JSON.stringify(data)}`;
        })
        .catch(error => console.error('Error:', error));
}

// Function to create a new user
function createUser(id, name) {
    fetch('/public/users', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            "id": id,
            "name": name
        })
    })
        .then(response => response.json())
        .then(data => {
            document.getElementById('response').innerText = `User Created: ${JSON.stringify(data)}`;
        })
        .catch(error => console.error('Error:', error));
}

// Function to update a user by ID
function updateUserById(id, name) {
    fetch(`/public/users`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            "id": id,
            "name": name
        })
    })
        .then(response => response.json())
        .then(data => {
            document.getElementById('response').innerText = `User ${id} Updated: ${JSON.stringify(data)}`;
        })
        .catch(error => console.error('Error:', error));
}

// Function to delete users by IDs
function deleteUsersByIds(ids) {
    fetch('/public/users', {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ ids: ids })
    })
        .then(response => response.json())
        .then(data => {
            document.getElementById('response').innerText = `Users Deleted: ${JSON.stringify(data)}`;
        })
        .catch(error => console.error('Error:', error));
}

// Function to retrieve a user by name
function getUserByName(name) {
    fetch(`/private/users/${name}`)
        .then(response => response.json())
        .then(data => {
            document.getElementById('response').innerText = `User ${name}: ${JSON.stringify(data)}`;
        })
        .catch(error => console.error('Error:', error));
}
