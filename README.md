# Hashword

Hashword is a password generator that uses various hardware specifications as well as a concatenation of a master password, a username/email, and the domain to generate a unique password.
A user on a different computer will never be able to generate the same password as you, as this program will get configurable information about your computer hardware and use that to help compute the hash that will be returned as your password.

## Installation

### Installing Git
###### Windows
> Use https://github.com/git-guides/install-git to install git on your windows machine.
###### macOS
> Download xcode developer tools by running ```git``` in console, and follow the onscreen prompts to install git.
###### Linux
> Open a terminal, and run ```apt install git -y``` to install git.

### Program Installation

In order to install and run this program, all you need to do it clone the repository with the following command:
```
git clone https://github.com/THollenkamp/Hashword.git
```
You can then edit the config.json to your liking, and disable/enable different hardware aspects that will be used to generate your password. Please note that if you change this config after you start generating passwords, you will not be able to generate the same password again unless you change the config back to the original state.

## Building

### Installing Maven
Follow the instructions on https://maven.apache.org/install.html to install maven.
Once maven is installed, in the project directory, run:
```
mvn clean install
```
The project is built! Follow the section below to run the program based on your operating system.

## Running

#### Windows:
Open a file explorer, navigate to the project directory, and double click ```run.bat```, or
open a command prompt, navigate to the project directory, and type ```run.bat```

#### macOS and Linux:
Open a terminal instance, navigate to the project directory, and execute ```./run.sh```

Then, enter your master password (this is not authenticated anywhere, but will determine what password is displayed), then your username/email, and the domain for the account, and your password will be displayed.

## NOTICE

This project does not store any passwords, however, if any of your hardware information changes, or you edit the ```config.json``` file,
you will no longer be able to replicate the original passwords without putting the settings back to how they were before you made any changes to the config/hardware. If you keep all of your hardware the same, and do not make any changes to the config, the program will consistently display the same passwords, depending on what you input for the 3 values in the project. Please make backups of any passwords you generate if you plan on changing any hardware information, or any variables in the config.

This is not necessarily the most secure method since the source code is public, however, it is a proof of concept that it would be possible to use hardware information to display a password. In a real world scenario, this code would be highly obfuscated in order to hide the methods of hashing and concatenation to make the program even harder to crack.
