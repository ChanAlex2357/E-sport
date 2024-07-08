<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="assets/css/bootstrap.min.css" />
    <link rel="stylesheet" href="assets/main.css" />
    <title>E-sport Manager</title>
    <style></style>
  </head>
  <body>
    <header class="">
      <nav class="navbar navbar-dark bg-prim-clr">
        <div class="container d-flex justify-content-center">
          <a class="navbar-brand"> E-sport Manager </a>
        </div>
      </nav>
    </header>
    <div class="container d-flex p-5">
      <div class="mx-auto w-50 border p-3">
        <div class="container d-flex justify-content-center">
          <a class="navbar-brand"> E-sport Manager </a>
        </div>
        <form action="login" method="post" class="d-block">
          <div class="form-floating mb-3">
            <input
              type="email"
              name="loginMail"
              id="inputMail"
              class="form-control"
              placeholder=""
              value="jean.dupont@example.com"
            />
            <label for="inputMail">Email</label>
          </div>
          <div class="form-floating mb-3">
            <input
              type="password"
              class="form-control"
              name="password"
              id="inputPass"
              placeholder=""
              value="password1"
            />
            <label for="inputPass">Password</label>
          </div>
          <div>
            <button type="submit" class="btn btn-primary">Log in</button>
          </div>
        </form>
      </div>
    </div>
  </body>
</html>
