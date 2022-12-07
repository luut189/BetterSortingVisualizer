## Getting Started

This is another sorting visualizer I made using the power of Java OOP.

## Features

Currently, this program supports 5 sorting algorithms:

- `Bubble Sort`
- `Insertion Sort`
- `Selection Sort`
- `Merge Sort`
- `Quick Sort`

## How to run

If you're using **Visual Studio Code**:

- If you clone this project into your local machine, good luck running it without **Code Runner**.

- Actually, even with **Code Runner**, you would probably need to edit the script inside `settings.json` in the `.vscode` folder.

- Your best bet is probably using your personal `settings.json` file.

> With the `Extension Pack for Java`, you should be able to run the source code.

If you're using **Replit**:

- I have it setup on **Replit** so you can just press `Run`.

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

    - `sfx`: the folder to maintain the sound component

    - `algorithm`: the folder to maintain the algorithms
    
    - `App.java`: the main class

Meanwhile, the compiled output files will be generated in the `bin` folder and ignored by `.gitignore` by default.

> If you are using Visual Studio Code, you can export `.jar` file if you have the `Extension Pack for Java` and it will be in the `build` folder.

## TODO

-  [ ] Adding more sorting algorithms:

    - [ ] `Heap Sort`

    - [ ] `Radix Sort`

    - [ ] `...`

- [ ] Refining the sound effect

- [ ] Actually implementing a function for **< 1 ms** delay, the current method:

```java
public void sleep(long durationInNs) {
    long startTime = System.nanoTime();
    long current = 0;
    do {
        current = System.nanoTime();
    } while(current - start < durationInNs);
}
```

This method **works**, however, it prevents almost everything else to be run.
