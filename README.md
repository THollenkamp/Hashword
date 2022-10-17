# Hashword

Hashword is a password generator that uses various hardware specifications as well as a concatenation of a master password, a username/email, and the domain to generate a unique password.
A user on a different computer will never be able to generate the same password as you as this program will get configurable information about your computer hardware and use that to help compute the hash that will be returned as your password.

## Installation

In order to install and run this program, all you need to do it clone the repository with the following command:
```
git clone https://github.com/THollenkamp/Hashword.git
```
You can then edit the config.json to your liking, and disable/enable different hardware aspects that will be used to generate your password. Please note that if you change this config after you start generating passwords, you will not be able to generate the same password again unless you change the config back to the original state.

## Running

Windows:
Run "run.bat"

macOS and Linux:
Run "run.sh"

Then, enter your master password(this is not authenticated anywhere, but will determine how your password is generated), then your username/email, and the domain for the account, and your password will be generated.
