import time as t

def sort(alist, lefthalf, righthalf):
    i, j, k = 0, 0, 0
    count = 0
    while i < len(lefthalf) and j < len(righthalf): # Condition to ensure that index doesn't go out of bounds
        count += 1
        if lefthalf[i] < righthalf[j]:  # Comparing the elemnts in corresponding left and right sub-arrays
            alist[k]=lefthalf[i]    # If the condition is true, update
            i=i+1
        else:
            alist[k]=righthalf[j]
            j=j+1
        k=k+1
    while i < len(lefthalf):    # If the right sub-array is exhausted
        alist[k]=lefthalf[i]    # Copy every element from left half to the sorted array
        i=i+1
        k=k+1
    while j < len(righthalf):    # If the left sub-array is exhausted
        alist[k]=righthalf[j]    # Copy every element from right half to the sorted array
        j=j+1
        k=k+1
    return count

def mergeSortTD(list):
    count = 0
    if len(list)>1: # Ensure that only the existing array is traversed
        mid = len(list)//2  # Obtain the 'int' value of 'mid' by floor division
        lefthalf = list[:mid]   # Defining the left sub-array
        righthalf = list[mid:]  # Defining the right sub-array
        mergeSortTD(lefthalf)   # Recursively calling "mergesortTD()"
        mergeSortTD(righthalf)
        count = sort(list, lefthalf, righthalf) # Keeping a track of the number of comparisons
    return count


if __name__ == '__main__':
    fileNames = ['data0.1024', 'data0.2048', 'data0.4096', 'data0.8192', 'data0.16384', 'data0.32768', 'data1.1024',
                 'data1.2048', 'data1.4096', 'data1.8192', 'data1.16384', 'data1.32768']

    # Empty lists to keep a track of the time taken and number of comparisons
    ydata_mergesortTD = []
    endTime_MergeSortTD = []

    for files in fileNames:
        print(files)
        f = open(files)

        nums = [int(x) for x in f.read().split("\n")[:-1]]

        # Calling mergesortTD() on the individual data sets
        startTime = t.time()
        k= mergeSortTD(nums)
        endTime_MergeSortTD.append(t.time() - startTime)

        print(nums)
        print("Total number of comparisons: " + str(k))
        ydata_mergesortTD.append(k)

    print(ydata_mergesortTD)


