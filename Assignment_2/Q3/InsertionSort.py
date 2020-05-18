import time as t
import matplotlib.pyplot as plt

def insertionSort(alist):
   for index in range(1,len(alist)):

        currentvalue = alist[index]
        position = index

        while position>0 and alist[position-1]>currentvalue:
             alist[position]=alist[position-1]
             position = position-1

        alist[position]=currentvalue


def bubbleSort(alist):
    for passnum in range(len(alist)-1,0,-1):
        for i in range(passnum):
            if alist[i]>alist[i+1]:
                temp = alist[i]
                alist[i] = alist[i+1]
                alist[i+1] = temp


if __name__ == '__main__':
    f = open("data1.txt", "w+")
    for i in range(1024):
        f.write("1 \n")
    for i in range(2048):
        f.write("11 \n")
    for i in range(4096):
        f.write("111 \n")
    for i in range(1024):
        f.write("1111 \n")



    totalTime = []
    xdata = ["Insertion Sort", "Bubble Sort"]
    f = open("data1.txt")
    nums = [int(x) for x in f.read().split("\n")[:-1]]
    print(nums)
    startTime = t.time()
    insertionSort(nums)
    totalTime.append(t.time() - startTime)
    print("After Insertion Sort: " + str(nums))

    startTime = t.time()
    bubbleSort(nums)
    totalTime.append(t.time() - startTime)
    print("After Bubble Sort: " + str(nums))

    print("Total time taken is : " + str(totalTime) + "\n")

    plt.scatter(xdata, totalTime)
    plt.show()
