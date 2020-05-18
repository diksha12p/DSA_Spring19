import matplotlib.pyplot as plt
import time as t

# Method to count the number of inversions i.e. Kendall Tau Distance
def numInversions(list1, list2, inv):
    returnlist = [] # List to contain the number of Inversions in each data set
    inversions = inv    # Initialization
    # i, j = 0, 0
    sum_length = len(list1) + len(list2) # Defining the length of the new array
    returnlist, inversions = merge(list1, list2, sum_length, returnlist, inversions)
    return returnlist, inversions

# Method to merge the sorted array
def merge(list1, list2, sum_length, returnList, inversions):
    i, j, k = 0, 0, 0
    for k in range(0, sum_length):
        if i == len(list1):     # if the first half is exhausted
            for l in range(j, len(list2)):
                returnList.append(list2[l])     # add all elements from first half
            break
        if j == len(list2):     # if the second half is exhausted
            for l in range(i, len(list2)):
                returnList.append(list1[l])     # add all elements from first half
            break
        if list1[i] < list2[j]:     # Comparing the elements in corresponding left and right sub-arrays
            returnList.append(list1[i])     # If condition is true, add to sorted array
            i += 1
        else:   # Comparing the elements in corresponding left and right sub-arrays
            returnList.append(list2[j])     # If condition is true, add to sorted array
            inversions = inversions + (len(list1) - i)  # Update the number of inversions
            j += 1
    return returnList, inversions

#Method to sort an array
# Method used here : Merge Sort
def sort(alist, count):
    newcount = count
    list_length = len(alist)
    if list_length == 0 or list_length == 1:      # for a null or single element list
        return alist, newcount
    first = []
    second = []
    for m in range(int(list_length / 2)):
        first.append(alist[m])

    for n in range(int(list_length / 2), list_length):
        second.append(alist[n])
    sorted_first, newcount = sort(first, newcount)
    sorted_second, newcount = sort(second, newcount)

    newlist, newcount = numInversions(sorted_first, sorted_second, newcount)
    return newlist, newcount


if __name__ == '__main__':
    fileNames = ['data0.1024', 'data0.2048', 'data0.4096', 'data0.8192', 'data0.16384', 'data0.32768', 'data1.1024',
                 'data1.2048','data1.4096', 'data1.8192', 'data1.16384', 'data1.32768']
    xdata = ['data0.1024', 'data0.2048', 'data0.4096', 'data0.8192', 'data0.16384', 'data0.32768', 'data1.1024',
             'data1.2048','data1.4096', 'data1.8192', 'data1.16384', 'data1.32768']
    inversionsList = []
    endTime = []
    for files in fileNames:
        print(files)
        f = open(files)

        nums = [int(x) for x in f.read().split("\n")[:-1]]
        print(nums)

        startTime = t.time()
        k, y = sort(nums, 0)
        endTime.append(t.time() - startTime)

        print("The sorted array is :" + str(k))
        print("#Inversions is " + str(y))

        inversionsList.append(y)

    print(endTime)
    print(inversionsList)
    plt.scatter(xdata, inversionsList)
    plt.savefig("Inversions.png")
    plt.show()

