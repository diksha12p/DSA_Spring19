import time as t
import matplotlib.pyplot as plt

def swap(arr,i,j):
    arr[i],arr[j] = arr[j],arr[i]   # Swap the values of the corresponding elements


def partition(array,start,end):
    median = int((end-start-1) / 2) # Obtaining the median
    median = median + start
    left_mark = start + 1

    # Finding the Pivot element using median of the three
    if (array[median]-array[end-1])*(array[start]-array[median]) >= 0: # IMPLIES : array[start] > array[median] > array[end-1]
        swap(array,start,median)

    elif (array[end-1] - array[median]) * (array[start]-array[end-1]) >=0:  # IMPLIES : array[start] > array[median] > array[end-1]
        k = end-1
        swap(array,start,k)
    pivot = array[start]

    for right_mark in range(start, end):
        if pivot > array[right_mark]:
            swap(array, left_mark, right_mark)
            left_mark = left_mark + 1
    swap(array, start, left_mark - 1)

    return left_mark - 1    # Returning index of partition element


# def find_median(a,b,c):
#     if (a-b)*(c-a) >= 0:
#         return a
#     elif (b-a)*(c-b) >= 0:
#         return b
#     else:
#         return c


def quickSortHelper(array,start,end):
    if start < end:
        split_point = partition(array,start,end)    # determining the pivot
        quickSortHelper(array,start,split_point)     # Recursively calling quicksortHelper()
        quickSortHelper(array,split_point+1,end)


def quickSort(array):
    quickSortHelper(array,0,len(array))


if __name__ == '__main__':
    ydata_quicksort = []
    xdata_quicksort = ['data0.1024', 'data0.2048', 'data0.4096', 'data0.8192', 'data0.16384', 'data0.32768',
                       'data1.1024', 'data1.2048', 'data1.4096', 'data1.8192', 'data1.16384', 'data1.32768']
    fileNames = ['data1.1024', 'data0.2048', 'data0.4096', 'data0.8192', 'data0.16384', 'data0.32768', 'data1.1024',
                 'data1.2048', 'data1.4096', 'data1.8192', 'data1.16384', 'data1.32768']
    for files in fileNames:
        print(files)
        f = open(files)

        nums = [int(x) for x in f.read().split("\n")[:-1]]
        print(nums)

        startTime = t.time()
        quickSort(nums)
        ydata_quicksort.append(t.time()-startTime)

        print(nums)
    print("TIME TAKEN :" + str(ydata_quicksort) + "\n")
    plt.scatter(xdata_quicksort, ydata_quicksort)
    plt.savefig("QuickSort.png")
    plt.show()

