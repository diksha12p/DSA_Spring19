import time as t


def farthestPair(arr):  # Function defined to calculate the farthest pair
    if len(arr) == 0:   # Ensuring that the array isn't empty
        print('Operation can\'t be performed on NULL ARRAY!')
    else:
        current_min = current_max = 0

    for i in range(0, len(arr)):
        if arr[i] <= current_min:   # On encountering an element less than the current element, swap
            current_min = arr[i]
        elif arr[i] > current_max:  # On encountering an element greater than the current element, swap
            current_max = arr[i]
        else:
            continue
    return current_min, current_max


if __name__ == "__main__":
    array1 = [5.2, 9.4, 20, -10, 21.1, 40, 50, -20]
    startTime = t.time()
    min, max = farthestPair(array1) # Calling the function 'farthestPair'
    timeTaken = t.time() - startTime    # Calculating the time taken for the fnction execution
    print(timeTaken)
    print(f'Farthest Pair: ({min}, {max}), EXPECTED: (-20.0, 50.0)')
