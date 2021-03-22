(function () {
    var $usernameFld, $passwordFld;
    var $firstNameFld, $lastNameFld, $roleFld;
    var $createBtn, $updateBtn;
    var $tbody
    $(main);

    var userService = new AdminUserServiceClient();
    var users = [];
    var selectedUser = null;

    function main() {
        $tbody = jQuery("#wbdv-tbody")
        $createBtn = $(".wbdv-create")
        $updateBtn = $(".wbdv-update")

        $usernameFld = $(".wbdv-username")
        $passwordFld = $(".wbdv-password")
        $firstNameFld = $(".wbdv-first-name")
        $lastNameFld = $(".wbdv-last-name")
        $roleFld = $(".wbdv-role")

        $updateBtn.click(updateUser)
        $createBtn.click(createUser)
        userService.findAllUsers().then(function (actualUsers) {
            users = actualUsers
            renderUsers(users)
        })
    }

    function createUser() {
        var newUser = {
            username: $usernameFld.val(),
            password: $passwordFld.val(),
            firstName: $firstNameFld.val(),
            lastName: $lastNameFld.val(),
            role: $roleFld.val()
        }
        userService.createUser(newUser)
            .then(function (actualUser) {
                  users.push(actualUser)
                  renderUsers(users)
        })
        resetInputs();
    }

    function deleteUser(event) {
        var deleteBtn = $(event.target)
        var index = deleteBtn.attr("id")
        var deleteId = users[index]._id
        userService.deleteUser(deleteId)
            .then(function (status) {
                users.splice(index, 1)
                renderUsers(users)
        })
    }


    function selectUser(event) {
        var selectBtn = $(event.target)
        var selectedId = selectBtn.attr("id")
        selectedUser = users.find(user => user._id === selectedId)
        $usernameFld.val(selectedUser.username)
        $passwordFld.val(selectedUser.password)
        $firstNameFld.val(selectedUser.firstName)
        $lastNameFld.val(selectedUser.lastName)
        $roleFld.val(selectedUser.role)
    }

    function updateUser() {
        selectedUser.userName = $usernameFld.val()
        selectedUser.password = $passwordFld.val()
        selectedUser.firstName = $firstNameFld.val()
        selectedUser.lastName = $lastNameFld.val()
        selectedUser.role = $roleFld.val()
        userService.updateUser(selectedUser._id, selectedUser)
            .then(function (status) {
                var index = users.findIndex(user => user._id === selectedUser._id)
                users[index] = selectedUser
                renderUsers(users)
        })
        resetInputs();
    }

    function renderUsers(users) {
        $tbody.empty()
        for(var i=0; i<users.length; i++) {
            var user = users[i]
            $tbody
                .append(`
          <tr>
              <td class="wbdv-username">${user.username}</td>
              <td class="wbdv-password hidePassword">${user.password}</td>
              <td class="wbdv-first-name">${user.firstName}</td>
              <td class="wbdv-last-name">${user.lastName}</td>
              <td class="wbdv-role">${user.role}</td>
              <td class="wbdv-actions">
                  <span class="float-right" style="white-space: nowrap">
                      <i id="${i}" class="fa-2x fa fa-times wbdv-remove"></i>
                      <i id="${user._id}" class="fa-2x fa fa-pencil wbdv-edit"></i>
                  </span>
              </td>
          </tr>
          `)
        }
        $(".wbdv-remove").click(deleteUser)
        $(".wbdv-edit").click(selectUser)
    }

    function resetInputs() {
        $usernameFld.val("");
        $passwordFld.val("");
        $lastNameFld.val("");
        $firstNameFld.val("");
        $roleFLd.val("");
        selectedUser = null;
    }


})();