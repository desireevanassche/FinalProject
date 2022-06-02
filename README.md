# FinalProject
<details>
  <summary>Table of Contents</summary>
  <ul>
    <li>
      <a href="#about-the-project">About The Project</a>
  </ul>
      <ul>
        <li><a href="#technologies-used">Technologies Used</a></li>
      </ul>
    </li>
  <ul>
    <li><a href="#how-it-works">How It Works</a></li>
  </ul>  
  <ul>
    <li><a href="#lessons-learned">Lessons Learned</a></li>
  </ul>
  <ul>
    <li><a href="#contact">Contact</a></li>
  </ul>

  <!-- <ul>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
    </ul> -->

</details>

## About The Project
Plant Daddy is an application that allows a user to create, read, and update a library of common house hold plants.





## Technologies Used
- MySQL Workbench
- Spring Tool Suite 4
- Atom
- ZSH shell
- Postman
- Tomcat
- Gradle
- AWS
- JPA
- Spring Boot REST
- REST API
- Spring Data JPA
- JUNIT
- Javascript
- HTML
- Bootstrap
- Angular
- Typescript
- Node
- VS Code

## How it Works
If a user is not signed in they will have the ability to read data from the plant library, as well as view blogs entries. The user is also presented a login button that will allow them to sign into their account, or register one if they don't have one already.

Once the user has created an account/logged in, the user will be presented with additional options such as creating their own personal garden, and adding posts to a forum.

| HTTP Verb | URI                  | Request Body | Response Body | Purpose |
|-----------|----------------------|--------------|---------------|---------|
| GET       | `/api/plants`        |              | Collection of representations of all plants  | **List** or **collection** endpoint |
| GET       | `/api/plants/1`      |              | Representation of plant `1` | **Retrieve** endpoint |
| GET       | `/api/plants/search/elk`   |              | Representation of plant with name or description of `snake` | **Retrieve** endpoint |
| POST      | `/api/plant`     | Representation of a new plant| Description of the result of the operation | **Create** endpoint |
| PUT       | `/api/plants/1`   | Representation of an update to plant `1` | | **Replace** endpoint |
| PUT       | `/api/plants/disable/1`   | Representation of active status set to false for plant  `1` | | **Replace** endpoint |






## Lessons Learned


## Contact

<h4>Desiree Vanassche</h4>
<ul>
<li><a href="https://www.linkedin.com/in/desiree-vanassche/" target="_blank">LinkedIn</a></li>
<li><a href="https://github.com/desireevanassche" target="_blank">Github</a></li>
</ul>

<h4>Lucas Paladini</h4>
<ul>
<li><a href="https://www.linkedin.com/in/lucas-paladini/" target="_blank">LinkedIn</a></li>
<li><a href="https://github.com/Lpaladini90" target="_blank">Github</a></li>
</ul>

<h4>Zack Gady</h4>
<ul>
<li><a href="https://www.linkedin.com/in/zack-gaddy/" target="_blank">LinkedIn</a></li>
<li><a href="https://github.com/ZackTheAwkward" target="_blank">Github</a></li>
</ul>





<!-- ## ACKNOWLEDGMENTS -->
