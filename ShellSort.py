# import matplotlib.pyplot as plt

def shell_sort(list1):
    count = 0
    h_values = [7, 3, 1]    # List containing the values for the h-strides

    #while len(h_values) > 0 :
    for startIndex in h_values:     # Choosing the value of first stride
        count += h_sort(list1, 0, startIndex)   # Keeping a track of number of comparisons
        print("After increments of size " + str(startIndex) + "\n The list is : ")
        print(list1)    # Printing the sorted list

    return count



def h_sort(list1, start, h_value):
    count = 0
    for i in range(start, len(list1), h_value) :
        current_value = list1[i]    # storing the first element in the variable 'current_value'
        position = i    # storing the i(th) index in 'position' variable
        count += 1
        while position - h_value >= 0 and list1[position - h_value] > current_value:    # Comparining every alternate h elements
            list1[position]=list1[position - h_value]   # Swapping if the condition is true
            position = position - h_value   # Updating position
            count += 1  # Increasing count for number of comparisons

        list1[position] = current_value    # Updating the list with h-sorted array

    return count


if __name__ == '__main__':
    fileNames = ['data0.1024', 'data0.2048', 'data0.4096', 'data0.8192', 'data0.16384', 'data0.32768', 'data1.1024',
                 'data1.2048','data1.4096', 'data1.8192', 'data1.16384', 'data1.32768']
    xdata = ['data0.1024', 'data0.2048', 'data0.4096', 'data0.8192', 'data0.16384', 'data0.32768', 'data1.1024',
             'data1.2048','data1.4096', 'data1.8192', 'data1.16384', 'data1.32768']
    ydata = []  # List to keep track of number of comparisons
    for files in fileNames:
        print(files)
        f = open(files)

        nums = [int(x) for x in f.read().split("\n")[:-1]]
        k = shell_sort(nums)
        print("Number of Comparisons : " + str(shell_sort(nums)))
        print(nums)

        ydata.append(k)

    print(ydata)
    # plt.scatter(xdata, ydata)
    # plt.savefig("ShellSort.png")
    # plt.show()
