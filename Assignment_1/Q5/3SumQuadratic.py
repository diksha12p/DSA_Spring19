import time as t
import matplotlib.pyplot as plt


def three_sum_quad(arr) :   # Function for calculation
    for i in range(0, len(arr)-2):
        j = i+1
        k = len(arr)-1
        count = 0
        while j<k:
            sum = arr[i] + arr[j] + arr[k]  # Calculating the sum of the three elements
            if sum==0:
                print('i =' + str(arr[i]) + 'j =' + arr[j] + 'k = ' + arr[k])   # If the sum is found to be zero, print out the elements
                count += 1
            elif sum >= 0:  # If sum is found greater than 0, decrement 'k'
                k -= 1
            else:
                j += 1  # If sum is found less than 0, increment 'k'
    return arr[i], arr[j], arr[k], count


if __name__ == "__main__":
    fileNames = ["8int.txt", "32int.txt", "128int.txt", "512int.txt", "1024int.txt", "4096int.txt", "4192int.txt", "8192int.txt"]
    xdata = [8, 32, 128, 512, 1024, 4096, 4192, 8192]
    totalTime =[]
    for files in fileNames: # Reading the files individually
        print(files)
        f = open(files)
        nums = [int(x) for x in f.read().split("\n")[:-1]]  # Array creation for individual elements
        nums.sort() # Sorting the array
        num = []
        startTime = t.time()
        num = three_sum_quad(nums)  # Assigning the result to 'sum'
        print('Value of count :' + str(num[3]))
        totalTime.append(t.time() - startTime)
        print(totalTime)

    # Plotting the results for the user
    plt.plot(xdata, totalTime)
    figure1 = plt.gcf()
    plt.show()
    figure1.savefig("3SumQuadratic.png")


