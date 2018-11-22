



def solution(S):
    stack = []
    text=S.split(" ")
    print((text))
    for value in text:
        if value.isdigit():
            if int(value) > 0 and int(value) <= 2000 :
                stack.append(value)
            else:
                return -1
            #print("STACK : " + str(stack))
        if value == 'DUP':
            if len(stack) == 0:
                return -1
            stack.append(stack[-1])
            #print("STACK : " + str(stack))
        if value == 'POP':
            if len(stack) == 0:
                return -1
            stack.pop()
        if value == '+':
            if len(stack) < 2:
                return -1
            num1 = stack.pop()
            num2 = stack.pop()
            #print(num1, num2)
            if int(num1)+int(num2) >2000:
                return -1
            else:
                stack.append(int(num1)+int(num2))
                #print("STACK : " + str(stack))
        if value == '-':
            if len(stack) < 2:
                return -1
            num1 = stack.pop()
            num2 = stack.pop()
           # print(num1 , num2)
            if int(num1)-int(num2) < 0:
                return -1
            else:
                stack.append(int(num1)-int(num2))
                #print("STACK : " + str(stack))


    #print("FINAL STACK : "+str(stack))
    return stack.pop()


if __name__ == "__main__":
    S = "13000 DUP 4 POP 5 DUP + DUP + -"
    #S = "DUP"
    print(solution(S))