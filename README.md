# 📊 Sorting Algorithm Visualizer

This Java-based project is a **visualization tool** for classic sorting algorithms. Each algorithm is animated in real time using bars whose **colors represent their current role** in the sorting process.

Great for learning, comparing algorithms, or just enjoying the beauty of sorting!

>This project was created as a semester assignment for the **PRO1 (Programování 1)** course at the **[University of Hradec Králové](https://www.uhk.cz)**.

---

## 🚀 Features

- Visualizes multiple sorting algorithms
- Color-coded real-time animations
- Adjustable array size and sorting delay
- Stopwatch showing algorithm duration
- Clean and simple GUI with dropdown selection

---

## 📋 Included Algorithms & Color Behavior

Each algorithm below is animated using the following default color logic:

```java
if (sortedIndices.contains(i))      // ✅ Final position
    GREEN
else if (i == activeIndex)          // 🔴 Main working element
    RED
else if (i == compareIndex)         // 🟡 Element being compared to red
    YELLOW
else                                // 🔵 Default / unsorted
    BLUE
```

---

## 🔁 Bubble Sort
A simple comparison-based algorithm that repeatedly steps through the list, compares adjacent elements, and swaps them if they're in the wrong order.

- **RED**: Left element in the compared pair  
- **YELLOW**: Right element in the compared pair  
- **GREEN**: Elements that are in their final position  
- **BLUE**: Unsorted elements  

## 🧩 Insertion Sort
Builds the final sorted array one item at a time by inserting elements into their correct position in the sorted part.

- **RED**: The key element being inserted  
- **YELLOW**: The element to the left being compared  
- **GREEN**: Sorted elements (usually marked at the end of each step)  
- **BLUE**: Elements not yet sorted  

## 🧮 Selection Sort
Finds the minimum element from the unsorted part and swaps it with the first unsorted element.

- **RED**: Current candidate for the smallest element  
- **YELLOW**: Element currently being checked  
- **GREEN**: Elements already in final position  
- **BLUE**: Elements not yet sorted  

## ⚡ Quick Sort
A divide-and-conquer algorithm that selects a pivot and partitions the array around it.

- **RED**: Current element being compared  
- **YELLOW**: Pivot or potential swap target  
- **GREEN**: Sorted (pivot) elements  
- **BLUE**: Unprocessed elements  

## 🧠 Merge Sort
Recursively divides the array and merges sorted halves.

- **RED**: Current element being merged  
- **YELLOW**: Corresponding merge candidate from opposite half  
- **GREEN**: Elements placed in final position  
- **BLUE**: Not yet processed  

## 🐚 Shell Sort
A generalization of insertion sort that allows exchange of far-apart elements.

- **RED**: Current element being inserted  
- **YELLOW**: Gap-distant element for comparison  
- **GREEN**: Sorted elements  
- **BLUE**: Still in progress  

## 🌲 Heap Sort
Uses a heap data structure to build a max-heap and extract the largest elements one by one.

- **RED**: Element currently being heapified  
- **YELLOW**: Left/right child being compared  
- **GREEN**: Final sorted elements (extracted from the heap)  
- **BLUE**: Not yet in heap or not processed  

## 🔢 Radix Sort
A non-comparative integer sorting algorithm that processes digits from least significant to most significant.

- **RED**: Element being placed based on current digit  
- **YELLOW**: Target position (if highlighted)  
- **GREEN**: All elements once sorting is done  
- **BLUE**: Default state during digit passes  

## ⏱️ Time Display
The elapsed time of each sorting process is shown in the navigation bar after completion – great for comparing performance.

## 🧑‍💻 How to Use
1. Clone the repository  
2. Run the project in your favorite Java IDE  
3. Select an algorithm, array size, and delay  
4. Click Start and watch it sort!  

## 📂 Technologies Used
- Java (Swing)  
- Basic multithreading  
- Functional GUI components (combo boxes, sliders, timers)  

## 🙌 Author
Created with love for algorithms, education, and satisfying animations.

Feel free to fork, contribute, or just enjoy watching numbers fall into place! 💚
