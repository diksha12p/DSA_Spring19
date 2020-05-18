def mergesort_BottomUp(alist):
    length = len(alist)
    aux = [0] * length  # Temporary array of specified length to store values
    sz = 1    # Initializing the 'size' to 1
    while sz < length:    # Till the last element of the array hasn't been reached
        index = 0
        while index < length:
            if index + 2 * sz - 1 >= length:   # Ensuring that the last index of sub-array doesn't exceed original array
                count = merge(alist, aux, index, index + sz - 1, length - 1)   # Perform merge on the sub-array
            else:
                count = merge(alist, aux, index, index + sz - 1, index + 2 * sz - 1) # Perform merge on the sorted
                                                                                     # sub-arrays
            index += 2 * sz     # Increment the sub-array index by twice the original 'size'
        sz += sz    # Increment the size
    return alist, count

def merge(alist, aux, low, mid, high):
    count = 0
    for k in range(low, high + 1):
        aux[k] = alist[k]  # copy items into temp

    first = low  # position in 1st half
    second = mid + 1  # position in 2nd half

    for k in range(low, high + 1):
        if first > mid:  # if the first half is exhausted
            alist[k] = aux[second]  # add all elements from second half
            second += 1

        elif second > high:  # if the second half is exhausted
            alist[k] = aux[first]  # add all elements from first half
            first += 1

        elif aux[second] < aux[first]:  # if value at second index is less than value at first index
            count += 1
            alist[k] = aux[second]  # add the smaller value to the sorted list
            second += 1

        else:  # iif value at first index is less than value at second index
            count += 1
            alist[k] = aux[first]  # add the smaller value to the sorted list
            first += 1

    return count    # To keep a track of number of comparisons


if __name__ == '__main__':
    fileNames = ['data0.1024', 'data0.2048', 'data0.4096', 'data0.8192', 'data0.16384', 'data0.32768', 'data1.1024',
                 'data1.2048', 'data1.4096', 'data1.8192', 'data1.16384', 'data1.32768']

    ydata_MergeSortBU = []

    for files in fileNames:

        print(files)
        f = open(files)

        nums = [int(x) for x in f.read().split("\n")[:-1]]
        print(nums)

        # Function call to bottom up version of 'Merge Sort'
        nums, k = mergesort_BottomUp(nums)
        print(nums)
        print("Total number of comparisons: " + str(k))
        ydata_MergeSortBU.append(k)

    print("BOTTOM UP: " +str(ydata_MergeSortBU))
