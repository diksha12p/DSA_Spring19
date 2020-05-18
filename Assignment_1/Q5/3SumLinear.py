def linearTypeSearch(data, a):  # Function definition for 'Linear' search
    coun = 0
    for i in range(len(data)):
        if data[i] == -a:   # If the data is found in the array, increment the counter
            coun += 1


def count(data):    # Function definition for maintaining the 'count'
    cnt = 0
    data.sort() # Sorting the array
    for i in range(len(data)):
        for j in range(i+1,len(data)):
            k = data[i] + data[j]   # Finding the sum of two data elements of the array
            if linearTypeSearch(data, k):   # Incrementing the counter if found through linear type search
                cnt += 1
    print('The number of triplets is :' + str(cnt)+ '\n')
    return cnt


if __name__ == "__main__":
    fileNames = ["8int.txt", "32int.txt", "128int.txt", "512int.txt", "1024int.txt", "4096int.txt","4192int.txt", "8192int.txt"]
    xdata = [8, 32, 128, 512 , 1024, 4096, 4192, 8192]
    for files in fileNames:
        print(files)
        f = open(files)

        nums = [int(x) for x in f.read().split("\n")[:-1]]  # Storing the data entries in the array
        count(nums)
