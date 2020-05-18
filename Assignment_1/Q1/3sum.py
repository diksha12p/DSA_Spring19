import time as t
import matplotlib.pyplot as plt


def count_naive(nums):  # To count the number of triplets that sum to 0
    cnt_naive = 0   # variable to store the value of count
    for i in range(len(nums)):  # Loop through the data entries
        for j in range(i+1,len(nums)):
            for k in range(j+1,len(nums)):
                if nums[i] + nums[j] + nums[k] == 0:
                    cnt_naive += 1
    return cnt_naive


def binarySearch(nums, low, high, x):   # Bianry Search to find the desired element in the data
    while low <= high:
        mid = int((low + high) / 2)
        if nums[mid] == x:
            return mid
        elif nums[mid] < x:  # Reassignment of lower bound
            low = mid +1
        else:   # Reassignment of higher bound
            high = mid -1
    return -1


def count_fast(nums):   # To count the number of triplets that sum to 0
    cnt_fast = 0
    nums.sort()     # Sorting the array, complexity: O(nlogn)
    for i in range(len(nums)):
        for j in range(i+1,len(nums)):
            a = nums[i]+nums[j]     # Calculating the sum
            if binarySearch(nums, 0, len(nums), -a):        # Binary Search on the data to find negative of the sum
                cnt_fast += 1   # If successful Binary Search, increment counter by 1
    return cnt_fast


if __name__ == "__main__":
    fileNames = ["8int.txt", "32int.txt", "128int.txt", "512int.txt", "1024int.txt", "4096int.txt", "4192int.txt",
                 "8192int.txt"]     # Reading the files containing data
    xdata = [8, 32, 128, 512 , 1024, 4096, 4192, 8192]
    ydata_naive = []    # Empty list which will hold the time needed for 'Naive' implementation for all files
    ydata_fast = []     # Empty list which will hold the time needed for 'Sophisticated' implementation for all files
    for files in fileNames:
        print(files)
        f = open(files)

        nums = [int(x) for x in f.read().split("\n")[:-1]]      # Obtaining every single entry of file and typecasting it to int before storing to array

        startTime = t.time()
        count_naive(nums)       # Calling "Naive" implementation
        ydata_naive.append(t.time() - startTime)

        startTime = t.time()
        count_fast(nums)        # Calling "Sophisticated" implementation
        ydata_fast.append(t.time() - startTime)

    print(ydata_naive)      # Printing the time required for each file
    print(ydata_fast)

    # Plotting the results
    plt.plot(xdata, ydata_naive, 'g')
    plt.plot(xdata, ydata_fast, 'r')
    plt.savefig("naive.png")
    plt.savefig("fast.png")
    plt.legend()
    plt.show()

