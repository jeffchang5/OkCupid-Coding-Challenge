## OKCupid Demo Application

This project is an implementation of the specs required by the OKCupid challenge. The app requires Android 5.0 and above.

### Goals

* Demonstrate a good understanding of software architecture and tooling that are important in maintaining a modern codebase.
* Conforming to Material Design standards in creating a predictable UI for users.
* Sensible file and project organization.
* Demonstrate the use of common Android libraries used commonly for modern codebases.
* Use dependency injection with Dagger 2 to produce decoupled objects and services.
* Utilize testing to ensure quality assurance as well as having a manageable codebase that quickens the process of bug fixing.


### Features

* Tap on a match in the special blend screen to be saved and viewed on the match screen.
* On tapped, the liked match is highlighted.
* When tapped on the match screen, it is removed and the respective like on the special blend screen is removed.
* Matches and likes persist on rotation and when the app is restarted.
* Matches and likes are saved.
* Handles no internet and unknown errors gracefully.
* Orders liked percentages in descending order.

### Screenshots

<table>
    <tr>
        <td><img style="width: 350px: height:auto; margin: 0 50px" src="/screenshots/horizontal.png"></img></td>
        <td><img src="/screenshots/vertical_liked.png"></img></td>
        <td><img src="/screenshots/vertical_matched.png"></img></td>
    </tr>
        <tr>
        <td align="center"><b>Horizontal Tablet<b></td>
        <td align="center"><b>Vertical Liked Special Blend</b></img></td>
        <td align="center"><b>Vertical Matched</b></img></td>
    </tr>
</table>