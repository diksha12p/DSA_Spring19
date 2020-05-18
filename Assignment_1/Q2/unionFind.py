import time as t
import matplotlib.pyplot as plt


def find(arr, index1, index2):  # Locating the element in the given data
    if arr[index1] == arr[index2]:
        return True
    else:
        return False


def find2(arr, index1, index2):     # Locating the element in the given data
    parent1, height1 = getParent(arr, index1, 0)  # Reaching the root parent by recursively calling 'getParent()' function
    parent2, height2 = getParent(arr, index2, 0)
    if parent1 == parent2:
        return True
    else:
        return False


def getParent(arr, l1, level):  # Recursive Function to get the root of a given node
    parent = 0
    #print(l1)
    if arr[l1] == l1:   # If the node is the root itself
        return l1, level

    parent, level = getParent(arr, arr[l1], level + 1)
    return parent, level


def union(arr, index1, index2):  # Union function to attach the two nodes for 'Quick Find'
    for i in arr:
        if arr[i] == arr[index2]:
            arr[i] = arr[index1]


def union2(arr, index1, index2):    # Union function to attach the two nodes for 'Quick Union'
    parent1, height1 = getParent(arr, index1, 0)
    parent2, height2 = getParent(arr, index2, 0)
    arr[parent1] = parent2


def union3(arr, index1, index2):    # Union function to attach the two nodes for 'Quick Union with Weight Balancing'
    parent1, height1 = getParent(arr, index1, 0)
    parent2, height2 = getParent(arr, index2, 0)
    if height1 < height2:   # Comparing the height of the two trees
        arr[parent1] = parent2  # Attaching the smaller size tree to the larger size tree
    else:
        arr[parent2] = parent1


if __name__ == "__main__":
    fileNames = ["8pair.txt", "32pair.txt", "128pair.txt", "512pair.txt", "1024pair.txt", "4096pair.txt", "8192pair.txt"]
    xdata = [8, 32, 128, 512, 1024, 4096, 8192]

    # Empty lists to store the time data for each computation
    findTimeData = []
    find2TimeData = []
    unionTimeData = []
    union2TimeData = []
    union3TimeData = []

    for files in fileNames:  # Reading the files to get the data
        print(files)
        findTime = 0
        find2Time = 0
        unionTime = 0
        union2Time = 0
        union3Time = 0

        # Setting the id's of all the elements
        arr1 = [i for i in range(8196)]
        arr2 = [i for i in range(8196)]
        arr3 = [i for i in range(8196)]
        #list1 = []
        #list2 = []
        # print(arr)
        f = open(files)
        lines = f.read().split("\n")[:-1]
        for line in lines:
            l1 = int(line.split(" ")[0])    # Getting every data from the pair to a separate array
            #list1.append(l1)
            l2 = int(line.split(" ")[1])
            #list2.append(l2)

                # for line in lines:
                #     l1 = int(line.split(" ")[0])
                #     l2 = int(line.split(" ")[1])
            startTime = t.time()
            status = find(arr1, l1, l2)     # Calling find function to get the status
            findTime += t.time() - startTime

            startTime = t.time()
            status = find2(arr2, l1, l2)
            find2Time += t.time() - startTime

            if status is False:     # In case the two pairs aren't connected, 'union' function is called
                startTime = t.time()
                union(arr1, l1, l2)
                unionTime += t.time() - startTime

                startTime = t.time()
                union2(arr2, l1, l2)
                union2Time += t.time() - startTime

                startTime = t.time()
                union3(arr3, l1, l2)
                union3Time += t.time() - startTime

        findTimeData.append(findTime)   # Post computation, the time required is appended in the corresponding list
        find2TimeData.append(find2Time)
        unionTimeData.append(unionTime)
        union2TimeData.append(union2Time)
        union3TimeData.append(union3Time)

    print(findTimeData)     # The time required is printed out for analysis purpose
    print(find2TimeData)
    print(unionTimeData)
    print(union2TimeData)
    print(union3TimeData)

    timeQuickFind = [x+y for x,y in zip(findTimeData, unionTimeData)]   # The net time is calculated by adding find and union times
    timeQuickUnion = [x+y for x,y in zip(find2TimeData, unionTimeData)]
    timeQuickUnionWB = [x+y for x,y in zip(find2TimeData, union3TimeData)]

    print('Time taken for Quick Find : ' + str(timeQuickFind))
    print('Time taken for Quick Union : ' + str(timeQuickUnion))
    print('Time taken for Quick Union with Weight Balance : ' + str(timeQuickUnionWB))

    # Plotting the data for analysis purpose
    plt.plot(xdata, timeQuickFind, 'r')
    # plt.figure()
    plt.plot(xdata, timeQuickUnion, 'g')
    # plt.figure()
    plt.plot(xdata, timeQuickUnionWB, 'b')
    plt.savefig('UnionFind.png')
    plt.show()
