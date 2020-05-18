# Method to merge two arrays, and count split inversions
def mergeandcount(list1, list2, inv):
    mergedlist = []
    inversions = inv
    i = 0
    j = 0
    length1 = len(list1)
    length2 = len(list2)
    newlength = length1 + length2

    for k in range(0, newlength):
        if i == length1:
            for l in range(j, length2):
                mergedlist.append(list2[l])
            break
        if j == length2:
           for l in range(i, length1):
               mergedlist.append(list1[l])
           break
        if list1[i] < list2[j]:
            mergedlist.append(list1[i])
            i += 1
        else:
            mergedlist.append(list2[j])
            inversions = inversions + (length1 - i)
            j += 1
    return mergedlist, inversions

#Method to sort an array
def sort(alist, count):
    newcount = count
    listlength = len(alist)
    if listlength == 0 or listlength == 1:
        return alist, newcount
    firsthalf = []
    secondhalf = []
    for elt in range(listlength/2):
        firsthalf.append(alist[elt])

    for elt in range(listlength/2, listlength):
        secondhalf.append(alist[elt])
    sortedfirst, newcount = sort(firsthalf, newcount)
    sortedsecond, newcount = sort(secondhalf, newcount)
    newlist, newcount = mergeandcount(sortedfirst, sortedsecond, newcount)
    return newlist, newcount

if __name__ == "__main__":
    fileNames = ["8int.txt", "32int.txt", "128int.txt", "512int.txt", "1024int.txt", "4096int.txt", "4192int.txt",
                     "8192int.txt"]     # Reading the files containing data

    for files in fileNames:
        print(files)
        f = open(files)

        nums = [int(x) for x in f.read().split("\n")[:-1]]      # Obtaining every single entry of file and typecasting it to int before storing to array


    print sort(nums, 0)

    print "testcase is "
    print sort(intarray, 0)[1]
