package br.com.lucaspires.challengexp

sealed class RequestState {
    class EmptyList : RequestState()
    class Error : RequestState()
    class NoConnection : RequestState()
    class Result<T>(result: T) : RequestState()
}