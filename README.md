# Steganography-and-Cryptography
## Description
The Cryptography Steganography project allows you to hide and retrieve secret messages within images using steganography techniques. The messages can also be encrypted for added security. Steganography is a form of encryption where the secret message is concealed within another medium, such as an image, making it harder to detect.

<img src="https://github.com/MYAUMUR/Steganography-and-Cryptography/assets/74320524/85f8f72e-2b79-4b75-983e-0e84ac077378" width="70%" height="70%">

## Features
- Hide a secret message within an image
- Encrypt the message before hiding it
- Retrieve and decrypt hidden messages from images

## Installation
1. Clone the repository:
```bash
git clone https://github.com/MYAUMUR/Steganography-and-Cryptography.git
```
2. Change into the project directory:
```bash
cd Steganography-and-Cryptography
```

## Usage
To run the provided JAR file named "Steganography_and_Cryptography.jar" with the image file in the same folder, follow the instructions below:

1. Make sure you have Java installed on your system. You can check by running the following command in the terminal or command prompt:
```bash
java -version
```
2. If Java is not installed, download and install the latest version of Java from the official website: https://www.java.com
3. Place the **"Steganography_and_Cryptography.jar"** file in a folder where you also have the image file you want to use for hiding or retrieving messages.
4. Open a terminal or command prompt.
5. Navigate to the directory where the JAR file is located using the `cd` command. For example, if both the JAR file and the image file are in the "Documents" folder, use the following command:
```bash
cd Documents
```
**Run the JAR file using the following command:**
```
java -jar Steganography.jar
```
6. The application should start running, and you will see the following prompt:
```bash
Task (hide, show, exit):
```
You can now interact with the application and perform the desired actions.

**Please note that when running the JAR file, the image file should be in the same folder as the JAR file.**

7. The application will prompt you with the following options:

- `hide` - Hide a secret message within an image.
- `show` - Retrieve and decrypt a hidden message from an image.
- `exit` - Exit the application.
8. To hide a message within an image:
- Choose the hide option.
- Provide the path to the input image file.
- Provide the path to the output image file where the message will be hidden.
- Enter the message to hide.
- Enter a password to encrypt the message (optional).
- The application will hide the encrypted message within the image and save it.
9. To retrieve and decrypt a hidden message from an image:
Choose the show option.
- Provide the path to the image file containing the hidden message.
- Enter the password used to encrypt the message (if applicable).
- The application will retrieve and decrypt the hidden message from the image and display it.
- Repeat the steps to hide or retrieve messages as needed.

## Example 1: Encrypting and decrypting a message:
```
Task (hide, show, exit):
> hide
Input image file:
> travel.png
Output image file:
> enc.png
Message to hide:
> My encrypted message!
Password:
> mypassword
Message saved in enc.png image.
Task (hide, show, exit):
> show
Input image file:
> enc.png
Password:
> mypassword
Message:
My encrypted message!
Task (hide, show, exit):
> exit
Bye!
```
## Example 2: Encrypting a message in a very small image
```
Task (hide, show, exit):
> hide
Input image file:
> small.png
Output image file:
> out2.png
Message to hide:
> abcdefghijk
Password:
> mypassword
The input image is not large enough to hold this message.
Task (hide, show, exit):
> exit
Bye!
```
