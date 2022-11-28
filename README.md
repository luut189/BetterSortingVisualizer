## Getting Started

This is another sorting visualizer I made using the power of Java OOP.

## Features

Currently, this program supports 4 sorting algorithms:

- `Bubble Sort`
- `Insertion Sort`
- `Selection Sort`
- `Merge Sort`

## How to run

If you're using **Visual Studio Code**:

- If you clone this project into your local machine, good luck running it without **Code Runner**.

- Actually, even with **Code Runner**, you would probably need to edit the script inside `settings.json` in the `.vscode` folder.

- Your best bet is probably using your personal `settings.json` file.

If you're running it via **Terminal** because you think you're cool:

```
git clone https://github.com/luut189/BetterSortingVisualizer.git
cd BetterSortingVisualizer
cd src
javac -d ../bin App.java
java -classpath ../bin App
```

If you're using something else, **why?**

## Folder Structure

The workspace contains:

- `.vscode`: the folder to maintain my settings

- `src`: the folder to maintain sources:

    - `gfx`: the folder to maintain the GUI component

    - `algorithm`: the folder to maintain the algorithms
    
    - `App.java`: the main class

Meanwhile, the compiled output files will be generated in the `bin` folder and ignored by `.gitignore` by default.